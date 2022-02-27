package com.zlf.iot.productprovider.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 13754
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 11:03
 * @Description:
 */

@Data
@TableName("device")
public class Device {

    private String deviceName;

    private String deviceType;

    private String productKey;

    private String deviceSecret;

    private String deviceStatus;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String clientId;
}
