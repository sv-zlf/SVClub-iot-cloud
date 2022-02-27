package com.zlf.iot.productprovider.controller;

import com.zlf.iot.productprovider.common.VO.GlobalResult;
import com.zlf.iot.productprovider.entity.*;
import com.zlf.iot.productprovider.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
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

    private   GlobalResult result;

    @PostMapping("")
    @ApiOperation(value = "创建产品",notes = "time、key、Secret可以省略")
    public GlobalResult createProduct(@RequestBody Product product) {

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


    @PutMapping("/productKey/{productKey}")
    @ApiOperation(value = "更新产品信息")
    public GlobalResult updateProduct(@PathVariable String productKey,@RequestParam String productName,@RequestParam String productMessage) {

        if (productService.getdetail_productName(productName)!=null){
            result = GlobalResult.build(500, "该产品名称已存在， 请更换名字",null);
            return result;
        }
        if (productService.getdetail_productKey(productKey)!=null){
            try {
                if (productService.updateProduct(productKey,productName,productMessage)) {
                    Product product=productService.getdetail_productKey(productKey);
                    result = GlobalResult.build(200, "更新产品信息成功",product);
                } else{
                    result = GlobalResult.build(500, "更新产品信息成功",null);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                result = GlobalResult.build(500, "更新产品信息失败，异常",null);
            }
        }
        else{
            result = GlobalResult.build(500, "该产品密钥不正确，更新信息失败",null);
        }
        return result;
    }
    @DeleteMapping("/productKey/{productKey}")
    @ApiOperation(value = "删除产品")
    public GlobalResult deleteProduct0(@PathVariable String productKey) {

        try {
           if(productService.deleteProduct0(productKey)){
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

    @DeleteMapping("/productName/{productName}")
    @ApiOperation(value = "删除产品")
    public GlobalResult deleteProduct1(@PathVariable String productName) {

        try {
            if(productService.deleteProduct1(productName)){
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

    @DeleteMapping("")
    @ApiOperation(value = "删除产品")
    public GlobalResult deleteProducts(@RequestParam List<String> productKeys) {

        try {
            int num= productService.deleteProducts(productKeys);
            result =GlobalResult.build(200, "删除产品成功",num);
        }
        catch (Exception e){
            result = GlobalResult.build(500, "删除产品失败",null);
        }
        return result;
    }



    @GetMapping("/productName/{productName}")
    @ApiOperation(value = "查看产品详细信息")
    public GlobalResult checkProduct(@PathVariable String productName) {

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
    @GetMapping("/productKey/{productKey}")
    @ApiOperation(value = "查看产品详细信息")
    public GlobalResult getPro_detail(@PathVariable String productKey) {

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
