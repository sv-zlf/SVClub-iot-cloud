package com.zlf.iot.productprovider.entity;

import com.baomidou.mybatisplus.annotation.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.sql.Date;

/**
 * @author 13754
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 11:03
 * @Description:
 */

@Data
@TableName("product")
public class Product {


    private String productName;

    private String productKey;

    private String productSecret;

    private String productType;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String productMessage;

}
