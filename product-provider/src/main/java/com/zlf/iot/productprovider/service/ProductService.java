package com.zlf.iot.productprovider.service;


import com.zlf.iot.productprovider.entity.Product;

import java.util.List;

/**
 * @author 13754
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 11:42
 * @Description:
 */

public interface ProductService {

    /**
      * @Description: todo
      * @Date: 2022/2/27 9:49 上午
      * @Param null:
      **/
    void createProduct(Product product);

    /**
     * @Description: 获取产品信息
     * @Date: 2022/2/27 9:50 上午
     * @Param start:
     * @Param pageSize:
     **/
    List<Product> getProduct(int start, int pageSize);

    /**
     * @Description: 通过产品密钥删除指定产品信息
     * @Date: 2022/2/27 10:35 上午
     * @Param productKey:
     **/
    Boolean deleteProduct0(String productKey);

    /**
     * @Description: 通过产品名字删除指定产品信息
     * @Date: 2022/2/27 10:37 上午
     * @Param productName:
     **/
    Boolean deleteProduct1(String productName);

    /**
     * @Description: 用产品名字获取指定产品信息
     * @Date: 2022/2/27 9:51 上午
     * @Param productName:
     **/
    Product getdetail_productName(String productName);

    /**
     * @Description: 用产品密钥获取指定产品信息
     * @Date: 2022/2/27 9:53 上午
     * @Param productKey:
     **/
    Product getdetail_productKey(String productKey);

    /**
     * @Description: 更新产品名称和产品描述
     * @Date: 2022/2/27 10:52 上午
     * @Param productKey:
     * @Param productName:
     * @Param productMessage:
     **/
    Boolean updateProduct(String productKey,String productName,String productMessage);

    /**
     * @Description: 批量删除产品
     * @Date: 2022/2/27 7:58 下午
     * @Param productKeys:
     **/
    int deleteProducts(List<String> productKeys);

    int getProductCount();

}
