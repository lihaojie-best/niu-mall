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
 * 订单设置表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oms_order_setting")
@ApiModel(value = "OmsOrderSettingPo对象", description = "订单设置表")
public class OmsOrderSettingPo extends Model<OmsOrderSettingPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("秒杀订单超时关闭时间(分)")
    @TableField("flash_order_overtime")
    private Integer flashOrderOvertime;

    @ApiModelProperty("正常订单超时时间(分)")
    @TableField("normal_order_overtime")
    private Integer normalOrderOvertime;

    @ApiModelProperty("发货后自动确认收货时间（天）")
    @TableField("confirm_overtime")
    private Integer confirmOvertime;

    @ApiModelProperty("自动完成交易时间，不能申请售后（天）")
    @TableField("finish_overtime")
    private Integer finishOvertime;

    @ApiModelProperty("订单完成后自动好评时间（天）")
    @TableField("comment_overtime")
    private Integer commentOvertime;


    public static final String ID = "id";

    public static final String FLASH_ORDER_OVERTIME = "flash_order_overtime";

    public static final String NORMAL_ORDER_OVERTIME = "normal_order_overtime";

    public static final String CONFIRM_OVERTIME = "confirm_overtime";

    public static final String FINISH_OVERTIME = "finish_overtime";

    public static final String COMMENT_OVERTIME = "comment_overtime";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
