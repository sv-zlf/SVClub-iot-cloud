package com.zlf.iot.productprovider.service.impl;


import com.zlf.iot.productprovider.dao.DeviceMapper;
import com.zlf.iot.productprovider.entity.Device;
import com.zlf.iot.productprovider.service.DeviceService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
/**
 *@Description : TODO
 *@Author : zlf
 *@Date : 2022/2/27 9:54 上午
 *@Version : 1.0
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceMapper deviceMapper;

    private  String str = "a0A0b1B2c1C3d2D1e3E2f4F3g5G7h4H6i5Ij4J9k5K6l6Lm7M7n8N8o9Op0PqQrRsStTuUv9VwWxXy8YzZ";

    @Override
    public void createDevice(String deviceName,String productKey ) {
        Device device=new Device();
        device.setDeviceName(deviceName);
        device.setProductKey(productKey);

        String deviceSecret= RandomStringUtils.random(16,str);
        device.setDeviceSecret(deviceSecret);

        Date date = new Date(System.currentTimeMillis());
        device.setCreateTime(date);
        device.setDeviceType("设备");
        device.setDeviceStatus("未激活");
        deviceMapper.createDevice(device);
    }

    @Override
    public  int deleteDevices(List<String> productKey, List<String> deviceName){
        return deviceMapper.deleteDevices(productKey,deviceName);
    }

    @Override
    public  Boolean deleteDevice_Pro(String productKey){
        return deviceMapper.deleteDevice_Pro(productKey);
    }
    @Override
    public Boolean updateclientId(String productKey,String deviceName, String clientId) {
        return deviceMapper.updateclientId(productKey,deviceName, clientId);
    }

    @Override
    public Boolean updateStatus(String clientId, String status) {
        return deviceMapper.updateStatus(clientId, status);
    }

    @Override
    public Device autheDevice(String deviceName, String deviceSecret) {
        return deviceMapper.autheDevice(deviceName, deviceSecret);
    }



    @Override
    public int getDeviceCount() {
        return deviceMapper.getDeviceCount();
    }

    @Override
    public List<Device> getDevices(int start, int pageSize) {
        return deviceMapper.getDevices(start, pageSize);
    }

    @Override
    public List<Device> getProductDevices(String productKey) {
        return deviceMapper.getProductDevices(productKey);
    }

    @Override
    public Device getDevice(String deviceName, String productKey) {
        return deviceMapper.getDevice(deviceName, productKey);
    }

    @Override
    public Boolean deleteDevice(String deviceName, String productKey) {
        return  deviceMapper.deleteDevice(deviceName, productKey);
    }

    @Override
    public Device getDeviceClientId(String clientId) {
        return deviceMapper.getDeviceClientId(clientId);
    }
}
