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
 * 会员积分成长规则表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_rule_setting")
@ApiModel(value = "UmsMemberRuleSettingPo对象", description = "会员积分成长规则表")
public class UmsMemberRuleSettingPo extends Model<UmsMemberRuleSettingPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("连续签到天数")
    @TableField("continue_sign_day")
    private Integer continueSignDay;

    @ApiModelProperty("连续签到赠送数量")
    @TableField("continue_sign_point")
    private Integer continueSignPoint;

    @ApiModelProperty("每消费多少元获取1个点")
    @TableField("consume_per_point")
    private BigDecimal consumePerPoint;

    @ApiModelProperty("最低获取点数的订单金额")
    @TableField("low_order_amount")
    private BigDecimal lowOrderAmount;

    @ApiModelProperty("每笔订单最高获取点数")
    @TableField("max_point_per_order")
    private Integer maxPointPerOrder;

    @ApiModelProperty("类型：0->积分规则；1->成长值规则")
    @TableField("type")
    private Integer type;


    public static final String ID = "id";

    public static final String CONTINUE_SIGN_DAY = "continue_sign_day";

    public static final String CONTINUE_SIGN_POINT = "continue_sign_point";

    public static final String CONSUME_PER_POINT = "consume_per_point";

    public static final String LOW_ORDER_AMOUNT = "low_order_amount";

    public static final String MAX_POINT_PER_ORDER = "max_point_per_order";

    public static final String TYPE = "type";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
