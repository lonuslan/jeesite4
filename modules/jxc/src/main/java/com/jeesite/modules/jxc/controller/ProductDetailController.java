/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.jxc.entity.ProductDetail;
import com.jeesite.modules.jxc.service.ProductDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;

/**
 * product_detailController
 * @author jxc
 * @version 2019-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/jxc/productDetail")
public class ProductDetailController extends BaseController {

	@Autowired
	private ProductDetailService productDetailService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ProductDetail get(String id, boolean isNewRecord) {
		return productDetailService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("jxc:productDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductDetail productDetail, Model model) {
		model.addAttribute("productDetail", productDetail);
		return "modules/jxc/productDetailList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("jxc:productDetail:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ProductDetail> listData(ProductDetail productDetail, HttpServletRequest request, HttpServletResponse response) {
		productDetail.setPage(new Page<>(request, response));
		Page<ProductDetail> page = productDetailService.findPage(productDetail);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("jxc:productDetail:view")
	@RequestMapping(value = "form")
	public String form(ProductDetail productDetail, Model model) {
		model.addAttribute("productDetail", productDetail);
		return "modules/jxc/productDetailForm";
	}

	/**
	 * 保存product_detail
	 */
	@RequiresPermissions("jxc:productDetail:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ProductDetail productDetail) {
		productDetailService.save(productDetail);
		return renderResult(Global.TRUE, text("保存product_detail成功！"));
	}
	
	/**
	 * 停用product_detail
	 */
	@RequiresPermissions("jxc:productDetail:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ProductDetail productDetail) {
		productDetail.setStatus(ProductDetail.STATUS_DISABLE);
		productDetailService.updateStatus(productDetail);
		return renderResult(Global.TRUE, text("停用product_detail成功"));
	}
	
	/**
	 * 启用product_detail
	 */
	@RequiresPermissions("jxc:productDetail:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ProductDetail productDetail) {
		productDetail.setStatus(ProductDetail.STATUS_NORMAL);
		productDetailService.updateStatus(productDetail);
		return renderResult(Global.TRUE, text("启用product_detail成功"));
	}
	
	/**
	 * 删除product_detail
	 */
	@RequiresPermissions("jxc:productDetail:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ProductDetail productDetail) {
		productDetailService.delete(productDetail);
		return renderResult(Global.TRUE, text("删除product_detail成功！"));
	}
	
}