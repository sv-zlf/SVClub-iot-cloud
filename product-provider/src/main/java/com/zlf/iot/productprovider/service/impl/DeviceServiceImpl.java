package com.zlf.iot.productprovider.service.impl;


import com.zlf.iot.productprovider.dao.DeviceMapper;
import com.zlf.iot.productprovider.entity.Device;
import com.zlf.iot.productprovider.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    
    @Override
    public void createDevice(Device device) {
        deviceMapper.createDevice(device);
    }

    @Override
    public void updateclientId(String deviceName, String clientId) {
        deviceMapper.updateclientId(deviceName, clientId);
    }

    @Override
    public Device autheDevice(String deviceName, String deviceSecret) {
        return deviceMapper.autheDevice(deviceName, deviceSecret);
    }

    @Override
    public void updateStatus(String clientId, String status) {
        deviceMapper.updateStatus(clientId, status);
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
    public void deleteDevice(String deviceName, String productKey) {
        deviceMapper.deleteDevice(deviceName, productKey);
    }

    @Override
    public Device getDeviceClientId(String clientId) {
        return deviceMapper.getDeviceClientId(clientId);
    }
}
