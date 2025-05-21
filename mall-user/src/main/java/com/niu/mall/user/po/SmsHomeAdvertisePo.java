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
 * 首页轮播广告表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_home_advertise")
@ApiModel(value = "SmsHomeAdvertisePo对象", description = "首页轮播广告表")
public class SmsHomeAdvertisePo extends Model<SmsHomeAdvertisePo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @ApiModelProperty("轮播位置：0->PC首页轮播；1->app首页轮播")
    @TableField("type")
    private Integer type;

    @TableField("pic")
    private String pic;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty("上下线状态：0->下线；1->上线")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("点击数")
    @TableField("click_count")
    private Integer clickCount;

    @ApiModelProperty("下单数")
    @TableField("order_count")
    private Integer orderCount;

    @ApiModelProperty("链接地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String PIC = "pic";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String STATUS = "status";

    public static final String CLICK_COUNT = "click_count";

    public static final String ORDER_COUNT = "order_count";

    public static final String URL = "url";

    public static final String NOTE = "note";

    public static final String SORT = "sort";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
