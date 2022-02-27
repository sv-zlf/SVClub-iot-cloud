package com.zlf.iot.productprovider.service;

import com.zlf.iot.productprovider.entity.Device;

import java.util.List;


public interface DeviceService {

    void createDevice(Device device);

    void updateclientId(String deviceName, String clientId);

    Device autheDevice(String deviceName, String deviceSecret);

    void updateStatus(String clientId, String status);

    int getDeviceCount();

    List<Device> getDevices(int start, int pageSize);

    List<Device> getProductDevices(String productKey);

    Device getDevice(String deviceName, String productKey);

    void deleteDevice(String deviceName, String productKey);

    Device getDeviceClientId(String clientId);
}
