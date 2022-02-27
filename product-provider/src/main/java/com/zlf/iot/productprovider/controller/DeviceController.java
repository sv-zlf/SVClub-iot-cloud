package com.zlf.iot.productprovider.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zlf.iot.productprovider.common.VO.*;
import com.zlf.iot.productprovider.entity.*;
import com.zlf.iot.productprovider.service.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 13754
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 10:40
 * @Description:
 */

@Api(tags = "物联网云设备服务")
@RestController
@RequestMapping("/device")
@CrossOrigin

public class DeviceController {

    @Autowired
    DeviceService deviceService;

    private GlobalResult result;


    @PostMapping("")
    @ApiOperation(value = "创建设备")
    public GlobalResult createDevice(@RequestParam String deviceName,@RequestParam String productKey) {


        if (deviceService.getDevice(deviceName,productKey)!=null) {
            result=GlobalResult.build(500, "该设备已存在",null);
            return  result;
        } else{
            try {
                deviceService.createDevice(deviceName,productKey);
                result = GlobalResult.build(200, "设备创建成功",null);
            }
            catch (Exception e){
                e.printStackTrace();
                result = GlobalResult.build(500, "设备创建失败",null);
            }
        }

        return result;
    }
    @DeleteMapping("/")
    @ApiOperation(value = "删除设备")
    public GlobalResult deleteDevice(@RequestParam List<String> productKey,@RequestParam List<String> deviceName) {

        try {
            int num=deviceService.deleteDevices(deviceName,productKey);
                result = GlobalResult.build(200, "设备已删除",num);
        }
        catch (Exception e){
            e.printStackTrace();
            result = GlobalResult.build(500, "删除设备失败",null);
        }
        return result;
    }

    @DeleteMapping("/{productKey}")
    @ApiOperation(value = "删除设备")
    public GlobalResult deleteDevice(@PathVariable String productKey) {

        try {
            if ( deviceService.deleteDevice_Pro(productKey)) {
                result = GlobalResult.build(200, "设备已删除",null);
            } else {
                result = GlobalResult.build(500, "删除设备失败",null);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result = GlobalResult.build(500, "删除设备失败",null);
        }
        return result;
    }

    @DeleteMapping("/{productKey}/{deviceName}")
    @ApiOperation(value = "删除设备")
    public GlobalResult deleteDevice(@PathVariable String productKey,@PathVariable String deviceName) {

        try {
            if (  deviceService.deleteDevice(deviceName,productKey)) {
                result = GlobalResult.build(200, "设备已删除",null);
            } else {
                result = GlobalResult.build(500, "删除设备失败",null);
            }
        }
        catch (Exception e){
            result = GlobalResult.build(500, "删除设备失败",null);
        }
        return result;
    }

    @PutMapping("/{productKey}/{deviceName}")
    @ApiOperation(value = "更新设备通话Id")
    public GlobalResult updateclientId(@PathVariable String productKey,@PathVariable String deviceName, @RequestParam String clientId){

        try {
            if(deviceService.updateclientId(productKey,deviceName,clientId)){
                result = GlobalResult.build(200, "更新设备通话Id成功",null);
            }
            else {
                result = GlobalResult.build(500, "更新设备通话Id失败",null);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result = GlobalResult.build(500, "更新设备通话Id异常",null);
        }
        return result;
    }

    @PutMapping("")
    @ApiOperation(value = "更新设备状态")
    public GlobalResult updateStatus(@RequestParam  String clientId, @RequestParam String deviceStatus){

        try {
            if(deviceService.updateStatus(clientId,deviceStatus)){
                result = GlobalResult.build(200, "更新设备状态成功",null);
            }
            else {
                result = GlobalResult.build(500, "更新设备状态失败",null);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result = GlobalResult.build(500, "更新设备状态异常",null);
        }
        return result;
    }

    @GetMapping("")
    @ApiOperation(value = "查看设备")
    public GlobalResult getDevices(@RequestParam int pageIndex,@RequestParam int pageSize) {

        int start=(pageIndex-1)*pageSize;
        try {
            List<Device> device = deviceService.getDevices(start,pageSize);
            int length=deviceService.getDeviceCount();
            if (device!=null) {
                result = GlobalResult.build(200, String.valueOf(length),device);
            } else {
                result = GlobalResult.build(500, "无设备信息", null);
            }
        }
        catch (Exception e){
            result = GlobalResult.build(500, "设备信息查询失败",null);
        }
        return result;
    }

    @GetMapping("/{productKey}/{deviceName}")
    @ApiOperation(value = "查看某个设备")
    public GlobalResult checkDevice(@PathVariable String productKey,@PathVariable String deviceName) {

        try {
            Device device=deviceService.getDevice(deviceName,productKey);
            if (device!=null) {
                result = GlobalResult.build(200, "设备查询成功",device);
            } else {
                result = GlobalResult.build(200, "设备查询失败",null);
            }
        }
        catch (Exception e){
            result = GlobalResult.build(500, "设备查询异常",null);
        }

        return result;
    }
    @GetMapping("/{productKey}")
    @ApiOperation(value = "查看指定产品设备")
    public GlobalResult getProductDevices(@PathVariable String productKey) {

        try {
            List<Device> devices=deviceService.getProductDevices(productKey);
            if (devices!=null) {
                result  = GlobalResult.build(200, "获取指定产品设备信息成功",devices);
            } else {
                result  = GlobalResult.build(200, "获取指定产品设备信息失败",devices);
            }
        }
        catch (Exception e){
            result  = GlobalResult.build(500, "获取产品设备信息异常",null);
        }

        return result;
    }

}
