/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jxc.service;

import java.io.IOException;
import java.util.List;

import com.jeesite.common.service.ServiceException;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.validator.ValidatorUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.jxc.entity.Product;
import com.jeesite.modules.jxc.dao.ProductDao;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import javax.validation.ConstraintViolationException;

/**
 * productService
 * @author lonuslan
 * @version 2019-07-18
 */
@Service
@Transactional(readOnly=true)
public class ProductService extends CrudService<ProductDao, Product> {

    /*@Override
    public void addDataScopeFilter(Product entity, String ctrlPermi) {
    entity.getSqlMap().getDataScope().addFilter("Product", "a.id", "a.create_by", ctrlPermi);
    }*/

    /**
     * 导入数据
     * @param file 文件输入流
     * @param isUpdateSupport 是否支持数据更新操作
     * @return
     */
    @Transactional(readOnly = false) //事务控制
	public String importData(MultipartFile file, Boolean isUpdateSupport){
	    if(file == null){
	        throw new ServiceException("请选择需要导入的文件");
        }
	    int successNum = 0; //成功标识
	    int failureNum = 0; //失败标识
	    StringBuilder successMsg = new StringBuilder(); //成功提示信息
	    StringBuilder failureMsg = new StringBuilder(); //错误提示信息

        try (ExcelImport ei = new ExcelImport(file, 2, 0)) {
            List<Product> list = ei.getDataList(Product.class);
            for (Product product : list) {
                try {//验证数据文件
                    ValidatorUtils.validateWithException(product);
                    //根据id从数据库获取product对象，判断是否存在（为null）
                    Product product1 = this.get(product.getId());
                    if(product1 == null){
                        //数据库没有这条数据，可以导入
                        save(product);
                        successNum++;
                        successMsg.append("<br/>"+successNum+"、商品名称"+product.getProductName()+"导入成功");
                    }else if(isUpdateSupport){
                        //product1 != null && isUpdateSupport = true 更新数据
                        this.save(product);
                        successNum++;
                        successMsg.append("<br/>"+successNum+"、商品名称"+product.getProductName()+"更新成功");
                    }else{
                        failureNum++;
                        failureMsg.append("<br/>"+failureNum+"、商品名称"+product.getProductName()+"已存在");
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、商品名称 " + product.getProductName() + " 导入失败：";
                    if (e instanceof ConstraintViolationException){
                        List<String> messageList = ValidatorUtils.extractPropertyAndMessageAsList((ConstraintViolationException)e, ": ");
                        for (String message : messageList) {
                            msg += message + "; ";
                        }
                    }else{
                        msg += e.getMessage();
                    }
                    failureMsg.append(msg);
                    logger.error(msg, e);
                }
            }

        } catch (Exception e){
            failureMsg.append(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        if(failureNum > 0){
            failureMsg.insert(0, "sorry, 共"+failureNum+"条数据导入失败，错误信息如下：");
            throw new ServiceException(failureMsg.toString());
        }else{
            successMsg.insert(0, "恭喜您，数据已全部导入成功，共"+successNum+"条数据，数据如下：");
        }
        return successMsg.toString();
    }
}