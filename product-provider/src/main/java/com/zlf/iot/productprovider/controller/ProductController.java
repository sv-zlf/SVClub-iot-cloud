package com.zlf.iot.productprovider.controller;

import com.zlf.iot.productprovider.common.VO.GlobalResult;
import com.zlf.iot.productprovider.entity.*;
import com.zlf.iot.productprovider.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 11:00
 * @Description:
 */

@Api(tags = "物联网云产品服务")
@RestController
@RequestMapping("/product")
@CrossOrigin

public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("")
    @ApiOperation(value = "创建产品")
    public GlobalResult createProduct( @RequestBody Product product) {

        GlobalResult result;
        if (productService.getdetail_productName(product.getProductName())!=null) {
            result = GlobalResult.build(500, "该产品已存在", null);
        }
        else {
            productService.createProduct(product);
            try {
                result = GlobalResult.build(200, "产品创建成功",null);
            }
            catch (Exception e){
                result = GlobalResult.build(500, "产品创建失败",null);
            }
        }
        return result;
    }

    @GetMapping("")
    @ApiOperation(value = "获取产品信息")
    public GlobalResult getProduct(@RequestParam int pageIndex,@RequestParam int pageSize){
        GlobalResult result;
       int start=(pageIndex-1)*pageSize;

       try {
           List<Product> product = productService.getProduct(start,pageSize);
           int length=productService.getProductCount();
           if (product!=null) {
               result = GlobalResult.build(200, String.valueOf(length),product);
           } else {
               result = GlobalResult.build(500, "无产品信息", null);
           }
       }
       catch (Exception e ){
           result = GlobalResult.build(500, "查询产品信息失败", null);
       }

        return result;
    }


    @DeleteMapping("")
    @ApiOperation(value = "删除产品")
    public GlobalResult deleteProduct(@RequestParam String productKey) {
        GlobalResult result ;

        try {
           if(productService.deleteProduct(productKey)){
               result = GlobalResult.build(200, "删除产品成功",null);
           }
           else {
               result = GlobalResult.build(500, "删除产品失败",null);
           }
        }
        catch (Exception e){
            result = GlobalResult.build(500, "删除产品失败",null);
        }
        return result;
    }

    @PostMapping("/productName/{productName}")
    @ApiOperation(value = "查看产品详细信息")
    public GlobalResult checkProduct(@PathVariable String productName) {
        GlobalResult result;
        try {
            Product product=productService.getdetail_productName(productName);
            if (product!=null) {
                result=GlobalResult.build(200, "产品信息获取成功",product);
            } else {
                result=GlobalResult.build(500, "不存在该产品",product);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result = GlobalResult.build(200, "产品信息获取失败",null);
        }
        return result;
    }
    @PostMapping("/productKey/{productKey}")
    @ApiOperation(value = "查看产品详细信息")
    public GlobalResult getPro_detail(@PathVariable String productKey) {
        GlobalResult result;
        try {
            Product product=productService.getdetail_productKey(productKey);
            if (product!=null) {
                result=GlobalResult.build(200, "产品信息获取成功",product);
            }
            else {
                result=GlobalResult.build(500, "不存在此产品",null);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result = GlobalResult.build(500, "产品信息获取失败",null);
        }
        return result;
    }
}
