package com.niu.mall.mbg.po;

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
 * 优惠券表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_coupon")
@ApiModel(value = "SmsCouponPo对象", description = "优惠券表")
public class SmsCouponPo extends Model<SmsCouponPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("优惠券类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    @TableField("type")
    private Integer type;

    @TableField("name")
    private String name;

    @ApiModelProperty("使用平台：0->全部；1->移动；2->PC")
    @TableField("platform")
    private Integer platform;

    @ApiModelProperty("数量")
    @TableField("count")
    private Integer count;

    @ApiModelProperty("金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("每人限领张数")
    @TableField("per_limit")
    private Integer perLimit;

    @ApiModelProperty("使用门槛；0表示无门槛")
    @TableField("min_point")
    private BigDecimal minPoint;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty("使用类型：0->全场通用；1->指定分类；2->指定商品")
    @TableField("use_type")
    private Integer useType;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;

    @ApiModelProperty("发行数量")
    @TableField("publish_count")
    private Integer publishCount;

    @ApiModelProperty("已使用数量")
    @TableField("use_count")
    private Integer useCount;

    @ApiModelProperty("领取数量")
    @TableField("receive_count")
    private Integer receiveCount;

    @ApiModelProperty("可以领取的日期")
    @TableField("enable_time")
    private Date enableTime;

    @ApiModelProperty("优惠码")
    @TableField("code")
    private String code;

    @ApiModelProperty("可领取的会员类型：0->无限时")
    @TableField("member_level")
    private Integer memberLevel;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String NAME = "name";

    public static final String PLATFORM = "platform";

    public static final String COUNT = "count";

    public static final String AMOUNT = "amount";

    public static final String PER_LIMIT = "per_limit";

    public static final String MIN_POINT = "min_point";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String USE_TYPE = "use_type";

    public static final String NOTE = "note";

    public static final String PUBLISH_COUNT = "publish_count";

    public static final String USE_COUNT = "use_count";

    public static final String RECEIVE_COUNT = "receive_count";

    public static final String ENABLE_TIME = "enable_time";

    public static final String CODE = "code";

    public static final String MEMBER_LEVEL = "member_level";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
