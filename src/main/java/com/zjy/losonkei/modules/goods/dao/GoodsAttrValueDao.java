/**
 * Copyright &copy; 2016-2017 <a>ZhangJunYan</a> All rights reserved.
 */
package com.zjy.losonkei.modules.goods.dao;

import com.zjy.losonkei.common.persistence.CrudDao;
import com.zjy.losonkei.common.persistence.annotation.MyBatisDao;
import com.zjy.losonkei.modules.goods.entity.GoodsAttrValue;

/**
 * 商品属性值表DAO接口
 * @author zjy
 * @version 2016-10-03
 */
@MyBatisDao
public interface GoodsAttrValueDao extends CrudDao<GoodsAttrValue> {
	
}