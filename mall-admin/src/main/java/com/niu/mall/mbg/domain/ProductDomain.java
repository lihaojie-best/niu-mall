package com.niu.mall.mbg.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品基本信息
 * </p>
 *
 * @author lihaojie
 * @since 2022-08-31
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("product")
@ApiModel(value = "ProductDomain对象", description = "商品基本信息")
public class ProductDomain extends Model<ProductDomain> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("属性id")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    @ApiModelProperty("属性名")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("颜色")
    @TableField("product_colour")
    private String productColour;

    @ApiModelProperty("生产公司")
    @TableField("product_company")
    private String productCompany;

    @ApiModelProperty("型号")
    @TableField("product_type")
    private String productType;

    @ApiModelProperty("价格")
    @TableField("product_price")
    private Integer productPrice;

    @ApiModelProperty("销售量  ")
    @TableField("product_sales_volume")
    private Integer productSalesVolume;

    @ApiModelProperty("库存")
    @TableField("product_stock")
    private Integer productStock;


    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_COLOUR = "product_colour";

    public static final String PRODUCT_COMPANY = "product_company";

    public static final String PRODUCT_TYPE = "product_type";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_SALES_VOLUME = "product_sales_volume";

    public static final String PRODUCT_STOCK = "product_stock";

    @Override
    public Serializable pkVal() {
        return this.productId;
    }

}
