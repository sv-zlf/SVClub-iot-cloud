package com.zlf.iot.productprovider.service;

import com.zlf.iot.productprovider.entity.Device;

import java.util.List;

/**
 *@Description : TODO
 *@Author : zlf
 *@Date : 2022/2/27 8:38 下午
 *@Version : 1.0
 **/
public interface DeviceService {

    /**
     * @Description: 创建设备
     * @Date: 2022/2/27 8:38 下午
     * @Param deviceName: 设备名称
     * @Param productKey: 产品密钥
     **/
    void createDevice(String deviceName,String productKey);

    /**
     * @Description: 批量删除设备
     * @Date: 2022/2/27 9:08 下午
     * @Param productKey:
     * @Param deviceName:
     **/
    int deleteDevices(List<String> productKey, List<String> deviceName);

    /**
     * @Description: 删除指定产品设备
     * @Date: 2022/2/27 9:19 下午
     * @Param productKey: 产品密钥
     **/
    Boolean deleteDevice_Pro(String productKey);

    /**
     * @Description: 删除指定设备
     * @Date: 2022/2/27 9:07 下午
     * @Param deviceName: 设备名称
     * @Param productKey: 产品密钥
     **/
    Boolean deleteDevice(String deviceName, String productKey);

    /**
     * @Description: 更新设备的clinerID
     * @Date: 2022/2/27 9:34 下午
     * @Param productKey: 产品密钥
     * @Param deviceName: 设备名称
     * @Param clientId:   通话ID
     **/
    Boolean updateclientId(String productKey,String deviceName, String clientId);

    /**
     * @Description: 更新设备状态
     * @Date: 2022/2/27 9:35 下午
     * @Param clientId: 设备通话ID
     * @Param status:  当前状态
     **/
    Boolean updateStatus(String clientId, String status);

    /**
     * @Description: 获取设备总和数量
     * @Date: 2022/2/27 9:57 下午
     **/
    int getDeviceCount();

    /**
     * @Description: 获取设备
     * @Date: 2022/2/27 9:57 下午
     * @Param start: 开始
     * @Param pageSize: 页面数量
     **/
    List<Device> getDevices(int start, int pageSize);

    /**
     * @Description: 获取该产品所有设备
     * @Date: 2022/2/27 9:58 下午
     * @Param productKey: 产品密钥
     **/
    List<Device> getProductDevices(String productKey);

    /**
     * @Description: 获取产品指定设备信息
     * @Date: 2022/2/27 9:59 下午
     * @Param deviceName: 设备名称
     * @Param productKey: 产品密钥
     **/
    Device getDevice(String deviceName, String productKey);

    Device getDeviceClientId(String clientId);

    Device autheDevice(String deviceName, String deviceSecret);

}
