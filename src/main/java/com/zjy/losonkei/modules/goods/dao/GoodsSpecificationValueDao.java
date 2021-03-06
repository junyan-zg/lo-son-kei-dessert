/**
 * Copyright &copy; 2016-2017 <a>ZhangJunYan</a> All rights reserved.
 */
package com.zjy.losonkei.modules.goods.dao;

import com.zjy.losonkei.common.persistence.CrudDao;
import com.zjy.losonkei.common.persistence.annotation.MyBatisDao;
import com.zjy.losonkei.modules.goods.entity.Goods;
import com.zjy.losonkei.modules.goods.entity.GoodsAll;
import com.zjy.losonkei.modules.goods.entity.GoodsSpecificationValue;

import java.util.List;

/**
 * 商品规格值表(参与购买)DAO接口
 * @author zjy
 * @version 2016-10-03
 */
@MyBatisDao
public interface GoodsSpecificationValueDao extends CrudDao<GoodsSpecificationValue> {
    public void trueDelete(String id);

    public List<GoodsSpecificationValue> getGoodsSpecificationValueListByGoodsId(Goods goods);

    public Integer getSpecificationGroup(GoodsAll goodsAll);
}