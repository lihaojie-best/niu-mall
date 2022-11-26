package com.niu.mall.mbg.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * sku的库存
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("pms_sku_stock")
@ApiModel(value = "PmsSkuStockPo对象", description = "sku的库存")
public class PmsSkuStockPo extends Model<PmsSkuStockPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("sku编码")
    @TableField("sku_code")
    private String skuCode;

    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty("预警库存")
    @TableField("low_stock")
    private Integer lowStock;

    @ApiModelProperty("展示图片")
    @TableField("pic")
    private String pic;

    @ApiModelProperty("销量")
    @TableField("sale")
    private Integer sale;

    @ApiModelProperty("单品促销价格")
    @TableField("promotion_price")
    private BigDecimal promotionPrice;

    @ApiModelProperty("锁定库存")
    @TableField("lock_stock")
    private Integer lockStock;

    @ApiModelProperty("商品销售属性，json格式")
    @TableField("sp_data")
    private String spData;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String SKU_CODE = "sku_code";

    public static final String PRICE = "price";

    public static final String STOCK = "stock";

    public static final String LOW_STOCK = "low_stock";

    public static final String PIC = "pic";

    public static final String SALE = "sale";

    public static final String PROMOTION_PRICE = "promotion_price";

    public static final String LOCK_STOCK = "lock_stock";

    public static final String SP_DATA = "sp_data";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
