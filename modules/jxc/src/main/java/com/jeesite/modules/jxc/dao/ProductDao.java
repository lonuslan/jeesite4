/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.jxc.entity.Product;

/**
 * productDAO接口
 * @author lonuslan
 * @version 2019-07-18
 */
@MyBatisDao
public interface ProductDao extends CrudDao<Product> {
	
}