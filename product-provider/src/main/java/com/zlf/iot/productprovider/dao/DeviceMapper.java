package com.zlf.iot.productprovider.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlf.iot.productprovider.entity.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zlf
 * @Date: 2021 12) 28) 11:28
 * @Description:
 */

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {


    @Insert("insert into device(deviceName, productKey,deviceSecret,deviceType,createTime,deviceStatus,clientId) values(#{deviceName}, #{productKey},#{deviceSecret},#{deviceType},#{createTime},#{deviceStatus},#{clientId})")
    void createDevice(Device device);


    @Delete({
            "<script>",
            "delete from device where productKey, deviceName IN",
            "<foreach collection='list,list' item='productKey,deviceName' open='(' separator=',' close=')'>",
            "#{productKey},#{deviceName}",
            "</foreach>",
            "</script>"
    })
    int deleteDevices(List<String> productKey, List<String> deviceName);

    @Delete("delete from device where productKey=#{productKey}")
    Boolean deleteDevice_Pro( @Param("productKey") String productKey);

    @Delete("delete  from device where deviceName=#{deviceName} and productKey=#{productKey}")
    Boolean deleteDevice(@Param("deviceName") String deviceName, @Param("productKey") String productKey);


    @Update("update device set deviceStatus=#{deviceStatus} where clientId=#{clientId} ")
    Boolean updateStatus(@Param("clientId") String clientId, @Param("deviceStatus") String deviceStatus);

    @Update("update device set clientId=#{clientId} where deviceName=#{deviceName} and productKey=#{productKey}")
    Boolean updateclientId(@Param("productKey") String productKey,@Param("deviceName") String deviceName, @Param("clientId") String clientId);


    @Select("select * from device where deviceName = #{deviceName} and deviceSecret=#{deviceSecret}")
    Device autheDevice(@Param("deviceName") String deviceName, @Param("deviceSecret") String deviceSecret);

    @Select("select * from device limit #{start} ,#{pageSize}")
    List<Device> getDevices(@Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from device limit #{start} ,#{pageSize}  where productKey=#{productKey} ")
    List<Device> getDevices_Pro(@Param("start") int start, @Param("pageSize") int pageSize,@Param("productKey") String productKey);

    @Select("select count(*) from device ")
    int getDeviceCount();

    @Select("select * from device where productKey=#{productKey}")
    List<Device> getProductDevices(@Param("productKey") String productKey);

    @Select("select * from device where deviceName=#{deviceName} and productKey=#{productKey}")
    Device getDevice(@Param("deviceName") String deviceName, @Param("productKey") String productKey);

    @Select("select * from device where clientId=#{clientId}")
    Device getDeviceClientId(@Param("clientId") String clientId);
}
