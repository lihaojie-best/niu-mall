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
 * 产品满减表(只针对同商品)
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
@TableName("pms_product_full_reduction")
@ApiModel(value = "PmsProductFullReductionPo对象", description = "产品满减表(只针对同商品)")
public class PmsProductFullReductionPo extends Model<PmsProductFullReductionPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("full_price")
    private BigDecimal fullPrice;

    @TableField("reduce_price")
    private BigDecimal reducePrice;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String FULL_PRICE = "full_price";

    public static final String REDUCE_PRICE = "reduce_price";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
