/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.service;

import java.util.List;

import com.jeesite.modules.jxc.dao.ProductDetailDao;
import com.jeesite.modules.jxc.entity.ProductDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * product_detailService
 * @author jxc
 * @version 2019-07-22
 */
@Service
@Transactional(readOnly=true)
public class ProductDetailService extends CrudService<ProductDetailDao, ProductDetail> {
	
	/**
	 * 获取单条数据
	 * @param productDetail
	 * @return
	 */
	@Override
	public ProductDetail get(ProductDetail productDetail) {
		return super.get(productDetail);
	}
	
	/**
	 * 查询分页数据
	 * @param productDetail 查询条件
	 * @param productDetail.page 分页对象
	 * @return
	 */
	@Override
	public Page<ProductDetail> findPage(ProductDetail productDetail) {
		return super.findPage(productDetail);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param productDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ProductDetail productDetail) {
		super.save(productDetail);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(productDetail.getId(), "productDetail_file");
	}
	
	/**
	 * 更新状态
	 * @param productDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ProductDetail productDetail) {
		super.updateStatus(productDetail);
	}
	
	/**
	 * 删除数据
	 * @param productDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ProductDetail productDetail) {
		super.delete(productDetail);
	}
	
}