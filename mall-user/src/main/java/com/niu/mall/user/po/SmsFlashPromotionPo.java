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
 * 限时购表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_flash_promotion")
@ApiModel(value = "SmsFlashPromotionPo对象", description = "限时购表")
public class SmsFlashPromotionPo extends Model<SmsFlashPromotionPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @ApiModelProperty("开始日期")
    @TableField("start_date")
    private Date startDate;

    @ApiModelProperty("结束日期")
    @TableField("end_date")
    private Date endDate;

    @ApiModelProperty("上下线状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("秒杀时间段名称")
    @TableField("create_time")
    private Date createTime;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String START_DATE = "start_date";

    public static final String END_DATE = "end_date";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
