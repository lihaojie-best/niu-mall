package com.niu.mall.mbg.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单操作历史记录
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oms_order_operate_history")
@ApiModel(value = "OmsOrderOperateHistoryPo对象", description = "订单操作历史记录")
public class OmsOrderOperateHistoryPo extends Model<OmsOrderOperateHistoryPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("操作人：用户；系统；后台管理员")
    @TableField("operate_man")
    private String operateMan;

    @ApiModelProperty("操作时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;


    public static final String ID = "id";

    public static final String ORDER_ID = "order_id";

    public static final String OPERATE_MAN = "operate_man";

    public static final String CREATE_TIME = "create_time";

    public static final String ORDER_STATUS = "order_status";

    public static final String NOTE = "note";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
