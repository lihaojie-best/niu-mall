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
 * 产品阶梯价格表(只针对同商品)
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
@TableName("pms_product_ladder")
@ApiModel(value = "PmsProductLadderPo对象", description = "产品阶梯价格表(只针对同商品)")
public class PmsProductLadderPo extends Model<PmsProductLadderPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("满足的商品数量")
    @TableField("count")
    private Integer count;

    @ApiModelProperty("折扣")
    @TableField("discount")
    private BigDecimal discount;

    @ApiModelProperty("折后价格")
    @TableField("price")
    private BigDecimal price;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String COUNT = "count";

    public static final String DISCOUNT = "discount";

    public static final String PRICE = "price";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
