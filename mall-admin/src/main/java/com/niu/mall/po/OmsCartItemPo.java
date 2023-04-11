package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oms_cart_item")
@ApiModel(value = "OmsCartItemPo对象", description = "购物车表")
public class OmsCartItemPo extends Model<OmsCartItemPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("product_sku_id")
    private Long productSkuId;

    @TableField("member_id")
    private Long memberId;

    @ApiModelProperty("购买数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("添加到购物车的价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("商品主图")
    @TableField("product_pic")
    private String productPic;

    @ApiModelProperty("商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("商品副标题（卖点）")
    @TableField("product_sub_title")
    private String productSubTitle;

    @ApiModelProperty("商品sku条码")
    @TableField("product_sku_code")
    private String productSkuCode;

    @ApiModelProperty("会员昵称")
    @TableField("member_nickname")
    private String memberNickname;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty("修改时间")
    @TableField("modify_date")
    private Date modifyDate;

    @ApiModelProperty("是否删除")
    @TableField("delete_status")
    private Integer deleteStatus;

    @ApiModelProperty("商品分类")
    @TableField("product_category_id")
    private Long productCategoryId;

    @TableField("product_brand")
    private String productBrand;

    @TableField("product_sn")
    private String productSn;

    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    @TableField("product_attr")
    private String productAttr;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_SKU_ID = "product_sku_id";

    public static final String MEMBER_ID = "member_id";

    public static final String QUANTITY = "quantity";

    public static final String PRICE = "price";

    public static final String PRODUCT_PIC = "product_pic";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_SUB_TITLE = "product_sub_title";

    public static final String PRODUCT_SKU_CODE = "product_sku_code";

    public static final String MEMBER_NICKNAME = "member_nickname";

    public static final String CREATE_DATE = "create_date";

    public static final String MODIFY_DATE = "modify_date";

    public static final String DELETE_STATUS = "delete_status";

    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    public static final String PRODUCT_BRAND = "product_brand";

    public static final String PRODUCT_SN = "product_sn";

    public static final String PRODUCT_ATTR = "product_attr";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
