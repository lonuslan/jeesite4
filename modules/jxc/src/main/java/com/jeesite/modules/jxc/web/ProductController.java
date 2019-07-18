/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.jxc.entity.Product;
import com.jeesite.modules.jxc.service.ProductService;

/**
 * productController
 * @author lonuslan
 * @version 2019-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/jxc/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Product get(String id, boolean isNewRecord) {
		return productService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("jxc:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, Model model) {
		model.addAttribute("product", product);
		return "modules/jxc/productList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("jxc:product:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Product> listData(Product product, HttpServletRequest request, HttpServletResponse response) {
		product.setPage(new Page<>(request, response));
		Page<Product> page = productService.findPage(product);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("jxc:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		model.addAttribute("product", product);
		return "modules/jxc/productForm";
	}

	/**
	 * 保存product
	 */
	@RequiresPermissions("jxc:product:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Product product) {
		productService.save(product);
		return renderResult(Global.TRUE, text("保存product成功！"));
	}
	
	/**
	 * 停用product
	 */
	@RequiresPermissions("jxc:product:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Product product) {
		product.setStatus(Product.STATUS_DISABLE);
		productService.updateStatus(product);
		return renderResult(Global.TRUE, text("停用product成功"));
	}
	
	/**
	 * 启用product
	 */
	@RequiresPermissions("jxc:product:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Product product) {
		product.setStatus(Product.STATUS_NORMAL);
		productService.updateStatus(product);
		return renderResult(Global.TRUE, text("启用product成功"));
	}
	
	/**
	 * 删除product
	 */
	@RequiresPermissions("jxc:product:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Product product) {
		productService.delete(product);
		return renderResult(Global.TRUE, text("删除product成功！"));
	}
	
}