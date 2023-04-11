package com.niu.mall.po;

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
 * 运费模版
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_feight_template")
@ApiModel(value = "PmsFeightTemplatePo对象", description = "运费模版")
public class PmsFeightTemplatePo extends Model<PmsFeightTemplatePo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @ApiModelProperty("计费类型:0->按重量；1->按件数")
    @TableField("charge_type")
    private Integer chargeType;

    @ApiModelProperty("首重kg")
    @TableField("first_weight")
    private BigDecimal firstWeight;

    @ApiModelProperty("首费（元）")
    @TableField("first_fee")
    private BigDecimal firstFee;

    @TableField("continue_weight")
    private BigDecimal continueWeight;

    @TableField("continme_fee")
    private BigDecimal continmeFee;

    @ApiModelProperty("目的地（省、市）")
    @TableField("dest")
    private String dest;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String CHARGE_TYPE = "charge_type";

    public static final String FIRST_WEIGHT = "first_weight";

    public static final String FIRST_FEE = "first_fee";

    public static final String CONTINUE_WEIGHT = "continue_weight";

    public static final String CONTINME_FEE = "continme_fee";

    public static final String DEST = "dest";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
