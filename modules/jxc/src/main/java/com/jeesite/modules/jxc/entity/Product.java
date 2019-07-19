/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * productEntity
 * @author lonuslan
 * @version 2019-07-18
 */
@Table(name="product", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="product_name", attrName="productName", label="商品名称", queryType=QueryType.LIKE),
		@Column(name="product_weight", attrName="productWeight", label="商品重量"),
		@Column(name="product_price", attrName="productPrice", label="商品价格"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Product extends DataEntity<Product> {

	@Valid
	@ExcelFields({
			@ExcelField(title = "商品id", attrName = "id", align = ExcelField.Align.CENTER),
			@ExcelField(title = "商品名称", attrName = "productName", align = ExcelField.Align.CENTER),
			@ExcelField(title = "商品价格", attrName = "productPrice", align = ExcelField.Align.CENTER),
			@ExcelField(title = "商品重量", attrName = "productWeight", align = ExcelField.Align.CENTER),
			@ExcelField(title = "更新时间", attrName = "updateDate", align = ExcelField.Align.CENTER),
			@ExcelField(title = "评论", attrName = "remarks", align = ExcelField.Align.CENTER)
	})

	private static final long serialVersionUID = 1L;
	private String productName;		// 商品名称
	private Double productWeight;		// 商品重量
	private Double productPrice;		// 商品价格
	
	public Product() {
		this(null);
	}

	public Product(String id){
		super(id);
	}
	
	@NotBlank(message="商品名称不能为空")
	@Length(min=0, max=100, message="商品名称长度不能超过 100 个字符")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@NotNull(message="商品重量不能为空")
	public Double getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}
	
	@NotNull(message="商品价格不能为空")
	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
}