package com.niu.mall.po;

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
 * 积分消费设置
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_integration_consume_setting")
@ApiModel(value = "UmsIntegrationConsumeSettingPo对象", description = "积分消费设置")
public class UmsIntegrationConsumeSettingPo extends Model<UmsIntegrationConsumeSettingPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("每一元需要抵扣的积分数量")
    @TableField("deduction_per_amount")
    private Integer deductionPerAmount;

    @ApiModelProperty("每笔订单最高抵用百分比")
    @TableField("max_percent_per_order")
    private Integer maxPercentPerOrder;

    @ApiModelProperty("每次使用积分最小单位100")
    @TableField("use_unit")
    private Integer useUnit;

    @ApiModelProperty("是否可以和优惠券同用；0->不可以；1->可以")
    @TableField("coupon_status")
    private Integer couponStatus;


    public static final String ID = "id";

    public static final String DEDUCTION_PER_AMOUNT = "deduction_per_amount";

    public static final String MAX_PERCENT_PER_ORDER = "max_percent_per_order";

    public static final String USE_UNIT = "use_unit";

    public static final String COUPON_STATUS = "coupon_status";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
