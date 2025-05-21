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
import java.util.Date;

/**
 * <p>
 * 优惠券使用、领取历史表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_coupon_history")
@ApiModel(value = "SmsCouponHistoryPo对象", description = "优惠券使用、领取历史表")
public class SmsCouponHistoryPo extends Model<SmsCouponHistoryPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("coupon_id")
    private Long couponId;

    @TableField("member_id")
    private Long memberId;

    @TableField("coupon_code")
    private String couponCode;

    @ApiModelProperty("领取人昵称")
    @TableField("member_nickname")
    private String memberNickname;

    @ApiModelProperty("获取类型：0->后台赠送；1->主动获取")
    @TableField("get_type")
    private Integer getType;

    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("使用状态：0->未使用；1->已使用；2->已过期")
    @TableField("use_status")
    private Integer useStatus;

    @ApiModelProperty("使用时间")
    @TableField("use_time")
    private Date useTime;

    @ApiModelProperty("订单编号")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("订单号码")
    @TableField("order_sn")
    private String orderSn;


    public static final String ID = "id";

    public static final String COUPON_ID = "coupon_id";

    public static final String MEMBER_ID = "member_id";

    public static final String COUPON_CODE = "coupon_code";

    public static final String MEMBER_NICKNAME = "member_nickname";

    public static final String GET_TYPE = "get_type";

    public static final String CREATE_TIME = "create_time";

    public static final String USE_STATUS = "use_status";

    public static final String USE_TIME = "use_time";

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_SN = "order_sn";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
