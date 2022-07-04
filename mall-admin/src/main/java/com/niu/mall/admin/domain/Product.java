package com.niu.mall.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品基本信息
 * </p>
 *
 * @author lhj
 * @since 2022-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 属性名
     */
    private String productName;

    /**
     * 颜色
     */
    private String productColour;

    /**
     * 生产公司
     */
    private String productCompany;

    /**
     * 型号
     */
    private String productType;

    /**
     * 价格
     */
    private Integer productPrice;

    /**
     * 销售量  
     */
    private Integer productSalesVolume;

    /**
     * 库存
     */
    private Integer productStock;


}
