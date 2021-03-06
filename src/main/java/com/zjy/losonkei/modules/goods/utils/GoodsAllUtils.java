package com.zjy.losonkei.modules.goods.utils;

import com.zjy.losonkei.common.utils.SpringContextHolder;
import com.zjy.losonkei.modules.goods.entity.Goods;
import com.zjy.losonkei.modules.goods.entity.GoodsAll;
import com.zjy.losonkei.modules.goods.entity.GoodsSpecificationValue;
import com.zjy.losonkei.modules.goods.service.GoodsAllService;
import com.zjy.losonkei.modules.goods.service.GoodsService;
import com.zjy.losonkei.modules.goods.service.GoodsSpecificationValueService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by zjy on 2016/10/28.
 */
public class GoodsAllUtils {

    private static GoodsSpecificationValueService goodsSpecificationValueService = SpringContextHolder.getBean(GoodsSpecificationValueService.class);

    private static GoodsService goodsService = SpringContextHolder.getBean(GoodsService.class);

    private static GoodsAllService goodsAllService = SpringContextHolder.getBean(GoodsAllService.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");

    private static Random random = new Random();

    /**
     * 填充实体GoodsAll的属性
     * @param goodsAll
     * @param fillGoods
     * @return
     */
    public static GoodsAll fillProperty(GoodsAll goodsAll,boolean fillGoods){
        GoodsSpecificationValue goodsSpecificationValue = new GoodsSpecificationValue();
        goodsSpecificationValue.setGoodsId(goodsAll.getGoodsId());
        goodsSpecificationValue.setSpecificationGroup(goodsAll.getSpecificationGroup());
        goodsAll.setGoodsSpecificationValues(goodsSpecificationValueService.findList(goodsSpecificationValue));
        if (fillGoods){
            goodsAll.setGoods(goodsService.get(goodsAll.getGoodsId()));
        }
        return goodsAll;
    }

    public static GoodsAll getGoodAllById(String id){
        return goodsAllService.get(id);
    }

    public static List<GoodsAll> getGoodsAllByGoodsId(String goodsId){
        GoodsAll goodsAll = new GoodsAll();
        goodsAll.setGoodsId(goodsId);
        goodsAll.setDelFlag(null);
        return goodsAllService.findList(goodsAll);
    }

    public static String createId(String prefix){
        StringBuffer sb = new StringBuffer();

        sb.append(prefix);
        sb.append(sdf.format(new Date()));

        int i = random.nextInt(1000);
        if(i < 10){
            sb.append("00");
        }else if(i < 100){
            sb.append("0");
        }
        sb.append(i);
        return sb.toString();
    }

    public static String getAllSpecificationDesc(GoodsAll goodsAll){
        StringBuilder sb = new StringBuilder();
        List<GoodsSpecificationValue> goodsSpecificationValues = goodsAll.getGoodsSpecificationValues();
        if (goodsSpecificationValues != null){
            for (GoodsSpecificationValue value : goodsSpecificationValues){
                sb.append(" ");
                sb.append(value.getSpecificationValue());
            }
        }
        return sb.toString();
    }

    //判断商品是否有效
    public static boolean isValid(GoodsAll goodsAll,Goods goods){
        return (GoodsAll.DEL_FLAG_NORMAL.equals(goodsAll.getDelFlag()) && Goods.STATE_ON_SALE.equals(goods.getState()));
    }

    //判断商品是否有效
    public static boolean isValid(GoodsAll goodsAll){
        if (goodsAll == null)
            return false;
        Goods goods = goodsAll.getGoods();
        if (goods == null){
            goods = goodsService.get(goodsAll.getGoodsId());
        }
        return (GoodsAll.DEL_FLAG_NORMAL.equals(goodsAll.getDelFlag()) && Goods.STATE_ON_SALE.equals(goods.getState()));
    }
}
