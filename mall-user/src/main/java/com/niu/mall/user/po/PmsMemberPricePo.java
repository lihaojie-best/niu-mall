package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品会员价格表
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
@TableName("pms_member_price")
@ApiModel(value = "PmsMemberPricePo对象", description = "商品会员价格表")
public class PmsMemberPricePo extends Model<PmsMemberPricePo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("member_level_id")
    private Long memberLevelId;

    @ApiModelProperty("会员价格")
    @TableField("member_price")
    private BigDecimal memberPrice;

    @TableField("member_level_name")
    private String memberLevelName;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String MEMBER_LEVEL_ID = "member_level_id";

    public static final String MEMBER_PRICE = "member_price";

    public static final String MEMBER_LEVEL_NAME = "member_level_name";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
