package com.zlf.iot.productprovider.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zlf.iot.productprovider.entity.Product;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * @Auther: 钟林锋
 * @Date: 2021 12) 28) 11:33
 * @Description:
 */

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Insert("insert into product(productName, productKey,productSecret,productType,createTime,productMessage) values(#{productName}, #{productKey},#{productSecret},#{productType},#{createTime},#{productMessage})")
    void createProduct(Product product);

    @Select("select * from product limit #{start} ,#{pageSize}")
    List<Product> getProduct(@Param("start") int start, @Param("pageSize") int pageSize);

    @Delete("delete from product where  productKey=#{productKey}")
    Boolean deleteProduct0(@Param("productKey") String productKey);

    @Delete("delete from product where productName=#{productName}")
    Boolean deleteProduct1(@Param("productName") String productName);

    @Select("select * from product where productName=#{productName}")
    Product getdetail_productName(@Param("productName") String productName);

    @Select("select * from product where productKey=#{productKey}")
    Product getdetail_productKey(@Param("productKey") String productKey);

    @Select("select count(*) from product ")
    int getProductCount();


    @Update("update product set productMessage=#{productMessage},productName=#{productName} where productKey=#{productKey}" )
    Boolean updateProduct(@Param("productKey") String productKey,@Param("productName") String productName,@Param("productMessage") String productMessage);

    @Delete({
            "<script>",
            "delete from product where productKey IN",
            "<foreach collection='list' item='productKeys' open='(' separator=',' close=')'>",
            "#{productKeys}",
            "</foreach>",
            "</script>"
    })
    int deleteProducts( List<String> productKeys);
}
