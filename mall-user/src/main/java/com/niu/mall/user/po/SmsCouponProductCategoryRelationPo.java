package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 优惠券和产品分类关系表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_coupon_product_category_relation")
@ApiModel(value = "SmsCouponProductCategoryRelationPo对象", description = "优惠券和产品分类关系表")
public class SmsCouponProductCategoryRelationPo extends Model<SmsCouponProductCategoryRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("coupon_id")
    private Long couponId;

    @TableField("product_category_id")
    private Long productCategoryId;

    @ApiModelProperty("产品分类名称")
    @TableField("product_category_name")
    private String productCategoryName;

    @ApiModelProperty("父分类名称")
    @TableField("parent_category_name")
    private String parentCategoryName;


    public static final String ID = "id";

    public static final String COUPON_ID = "coupon_id";

    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    public static final String PRODUCT_CATEGORY_NAME = "product_category_name";

    public static final String PARENT_CATEGORY_NAME = "parent_category_name";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
