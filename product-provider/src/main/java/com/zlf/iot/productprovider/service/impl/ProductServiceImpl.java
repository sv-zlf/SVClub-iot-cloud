package com.zlf.iot.productprovider.service.impl;

import com.zlf.iot.productprovider.dao.ProductMapper;

import com.zlf.iot.productprovider.entity.Product;
import com.zlf.iot.productprovider.service.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author 13754
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 11:44
 * @Description:
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    private String str = "a0A0b1B2c1C3d2D1e3E2f4F3g5G7h4H6i5Ij4J9k5K6l6Lm7M7n8N8o9Op0PqQrRsStTuUv9VwWxXy8YzZ";

    @Override
    public void createProduct(Product product) {
        String ProductKey= RandomStringUtils.random(10,str);
        product.setProductKey(ProductKey);

        String productSecret=RandomStringUtils.random(16,str);
        product.setProductSecret(productSecret);


        Date date = new Date(System.currentTimeMillis());
        product.setCreateTime(date);
        productMapper.createProduct(product);
        System.out.println("创建产品");
    }

    @Override
    public List<Product> getProduct(int start, int pageSize) {
        return productMapper.getProduct(start, pageSize);
    }

    @Override
    public Boolean deleteProduct(String productKey) {
       return productMapper.deleteProduct(productKey);
    }

    @Override
    public Product getdetail_productName(String productName) {
        return productMapper.getdetail_productName(productName);
    }

    @Override
    public Product getdetail_productKey(String productKey) {
        return productMapper.getdetail_productKey(productKey);
    }
    @Override
    public int getProductCount() {
        return productMapper.getProductCount();
    }

    @Override
    public List<Product> getAllProduct() {
        return productMapper.getAllProduct();
    }


}
