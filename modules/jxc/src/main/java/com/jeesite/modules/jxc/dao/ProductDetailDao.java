/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.jxc.entity.ProductDetail;

/**
 * product_detailDAO接口
 * @author jxc
 * @version 2019-07-22
 */
@MyBatisDao
public interface ProductDetailDao extends CrudDao<ProductDetail> {
	
}