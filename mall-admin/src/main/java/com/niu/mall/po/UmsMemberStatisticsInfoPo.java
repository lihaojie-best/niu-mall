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
 * 会员统计信息
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_statistics_info")
@ApiModel(value = "UmsMemberStatisticsInfoPo对象", description = "会员统计信息")
public class UmsMemberStatisticsInfoPo extends Model<UmsMemberStatisticsInfoPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @ApiModelProperty("累计消费金额")
    @TableField("consume_amount")
    private BigDecimal consumeAmount;

    @ApiModelProperty("订单数量")
    @TableField("order_count")
    private Integer orderCount;

    @ApiModelProperty("优惠券数量")
    @TableField("coupon_count")
    private Integer couponCount;

    @ApiModelProperty("评价数")
    @TableField("comment_count")
    private Integer commentCount;

    @ApiModelProperty("退货数量")
    @TableField("return_order_count")
    private Integer returnOrderCount;

    @ApiModelProperty("登录次数")
    @TableField("login_count")
    private Integer loginCount;

    @ApiModelProperty("关注数量")
    @TableField("attend_count")
    private Integer attendCount;

    @ApiModelProperty("粉丝数量")
    @TableField("fans_count")
    private Integer fansCount;

    @TableField("collect_product_count")
    private Integer collectProductCount;

    @TableField("collect_subject_count")
    private Integer collectSubjectCount;

    @TableField("collect_topic_count")
    private Integer collectTopicCount;

    @TableField("collect_comment_count")
    private Integer collectCommentCount;

    @TableField("invite_friend_count")
    private Integer inviteFriendCount;

    @ApiModelProperty("最后一次下订单时间")
    @TableField("recent_order_time")
    private Date recentOrderTime;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String CONSUME_AMOUNT = "consume_amount";

    public static final String ORDER_COUNT = "order_count";

    public static final String COUPON_COUNT = "coupon_count";

    public static final String COMMENT_COUNT = "comment_count";

    public static final String RETURN_ORDER_COUNT = "return_order_count";

    public static final String LOGIN_COUNT = "login_count";

    public static final String ATTEND_COUNT = "attend_count";

    public static final String FANS_COUNT = "fans_count";

    public static final String COLLECT_PRODUCT_COUNT = "collect_product_count";

    public static final String COLLECT_SUBJECT_COUNT = "collect_subject_count";

    public static final String COLLECT_TOPIC_COUNT = "collect_topic_count";

    public static final String COLLECT_COMMENT_COUNT = "collect_comment_count";

    public static final String INVITE_FRIEND_COUNT = "invite_friend_count";

    public static final String RECENT_ORDER_TIME = "recent_order_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
