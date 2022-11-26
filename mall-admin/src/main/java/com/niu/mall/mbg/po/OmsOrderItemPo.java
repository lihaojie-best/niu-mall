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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单中所包含的商品
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oms_order_item")
@ApiModel(value = "OmsOrderItemPo对象", description = "订单中所包含的商品")
public class OmsOrderItemPo extends Model<OmsOrderItemPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("订单编号")
    @TableField("order_sn")
    private String orderSn;

    @TableField("product_id")
    private Long productId;

    @TableField("product_pic")
    private String productPic;

    @TableField("product_name")
    private String productName;

    @TableField("product_brand")
    private String productBrand;

    @TableField("product_sn")
    private String productSn;

    @ApiModelProperty("销售价格")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty("购买数量")
    @TableField("product_quantity")
    private Integer productQuantity;

    @ApiModelProperty("商品sku编号")
    @TableField("product_sku_id")
    private Long productSkuId;

    @ApiModelProperty("商品sku条码")
    @TableField("product_sku_code")
    private String productSkuCode;

    @ApiModelProperty("商品分类id")
    @TableField("product_category_id")
    private Long productCategoryId;

    @ApiModelProperty("商品促销名称")
    @TableField("promotion_name")
    private String promotionName;

    @ApiModelProperty("商品促销分解金额")
    @TableField("promotion_amount")
    private BigDecimal promotionAmount;

    @ApiModelProperty("优惠券优惠分解金额")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    @ApiModelProperty("积分优惠分解金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount;

    @ApiModelProperty("该商品经过优惠后的分解金额")
    @TableField("real_amount")
    private BigDecimal realAmount;

    @TableField("gift_integration")
    private Integer giftIntegration;

    @TableField("gift_growth")
    private Integer giftGrowth;

    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    @TableField("product_attr")
    private String productAttr;


    public static final String ID = "id";

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_SN = "order_sn";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_PIC = "product_pic";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_BRAND = "product_brand";

    public static final String PRODUCT_SN = "product_sn";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_QUANTITY = "product_quantity";

    public static final String PRODUCT_SKU_ID = "product_sku_id";

    public static final String PRODUCT_SKU_CODE = "product_sku_code";

    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    public static final String PROMOTION_NAME = "promotion_name";

    public static final String PROMOTION_AMOUNT = "promotion_amount";

    public static final String COUPON_AMOUNT = "coupon_amount";

    public static final String INTEGRATION_AMOUNT = "integration_amount";

    public static final String REAL_AMOUNT = "real_amount";

    public static final String GIFT_INTEGRATION = "gift_integration";

    public static final String GIFT_GROWTH = "gift_growth";

    public static final String PRODUCT_ATTR = "product_attr";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
