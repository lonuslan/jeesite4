/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.jxc.entity.Product;
import com.jeesite.modules.jxc.dao.ProductDao;

/**
 * productService
 * @author lonuslan
 * @version 2019-07-18
 */
@Service
@Transactional(readOnly=true)
public class ProductService extends CrudService<ProductDao, Product> {
	

	
}