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
 * 限时购通知记录
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_flash_promotion_log")
@ApiModel(value = "SmsFlashPromotionLogPo对象", description = "限时购通知记录")
public class SmsFlashPromotionLogPo extends Model<SmsFlashPromotionLogPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("member_id")
    private Integer memberId;

    @TableField("product_id")
    private Long productId;

    @TableField("member_phone")
    private String memberPhone;

    @TableField("product_name")
    private String productName;

    @ApiModelProperty("会员订阅时间")
    @TableField("subscribe_time")
    private Date subscribeTime;

    @TableField("send_time")
    private Date sendTime;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String MEMBER_PHONE = "member_phone";

    public static final String PRODUCT_NAME = "product_name";

    public static final String SUBSCRIBE_TIME = "subscribe_time";

    public static final String SEND_TIME = "send_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
