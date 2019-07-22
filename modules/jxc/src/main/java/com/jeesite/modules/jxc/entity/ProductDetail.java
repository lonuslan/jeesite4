/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * product_detailEntity
 * @author jxc
 * @version 2019-07-22
 */
@Table(name="product_detail", alias="a", columns={
		@Column(name="id", attrName="id", label="商品编号", isPK=true),
		@Column(name="product_from", attrName="productFrom", label="商品始发地"),
		@Column(name="product_to", attrName="productTo", label="商品目的地"),
		@Column(name="direct_type", attrName="directType", label="所属类型"),
		@Column(name="director", attrName="director", label="经手人"),
		@Column(name="direct_adress", attrName="directAdress", label="出发地"),
		@Column(name="direct_repository", attrName="directRepository", label="所属仓库"),
		@Column(name="status", attrName="status", label="状态", isUpdate=false),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remark", attrName="remark", label="remark"),
		@Column(name="verify_type", attrName="verifyType", label="verify_type"),
		@Column(name="product_id", attrName="productId", label="商品id"),
	}, orderBy="a.update_date DESC"
)
public class ProductDetail extends DataEntity<ProductDetail> {
	
	private static final long serialVersionUID = 1L;
	private String productFrom;		// product_from
	private String productTo;		// product_to
	private String directType;		// direct_type
	private String director;		// director
	private String directAdress;		// direct_adress
	private String directRepository;		// direct_repository
	private String remark;		// remark
	private String verifyType;		// verify_type
	private String productId;		// product_id
	
	public ProductDetail() {
		this(null);
	}

	public ProductDetail(String id){
		super(id);
	}
	
	@NotBlank(message="product_from不能为空")
	@Length(min=0, max=255, message="product_from长度不能超过 255 个字符")
	public String getProductFrom() {
		return productFrom;
	}

	public void setProductFrom(String productFrom) {
		this.productFrom = productFrom;
	}
	
	@NotBlank(message="product_to不能为空")
	@Length(min=0, max=255, message="product_to长度不能超过 255 个字符")
	public String getProductTo() {
		return productTo;
	}

	public void setProductTo(String productTo) {
		this.productTo = productTo;
	}
	
	@Length(min=0, max=255, message="direct_type长度不能超过 255 个字符")
	public String getDirectType() {
		return directType;
	}

	public void setDirectType(String directType) {
		this.directType = directType;
	}
	
	@NotBlank(message="director不能为空")
	@Length(min=0, max=255, message="director长度不能超过 255 个字符")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	@NotBlank(message="direct_adress不能为空")
	@Length(min=0, max=255, message="direct_adress长度不能超过 255 个字符")
	public String getDirectAdress() {
		return directAdress;
	}

	public void setDirectAdress(String directAdress) {
		this.directAdress = directAdress;
	}
	
	@NotBlank(message="direct_repository不能为空")
	@Length(min=0, max=255, message="direct_repository长度不能超过 255 个字符")
	public String getDirectRepository() {
		return directRepository;
	}

	public void setDirectRepository(String directRepository) {
		this.directRepository = directRepository;
	}
	
	@Length(min=0, max=500, message="remark长度不能超过 500 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@NotBlank(message="verify_type不能为空")
	@Length(min=0, max=20, message="verify_type长度不能超过 20 个字符")
	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	
	@NotBlank(message="product_id不能为空")
	@Length(min=0, max=64, message="product_id长度不能超过 64 个字符")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}