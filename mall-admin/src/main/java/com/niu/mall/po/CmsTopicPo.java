package com.niu.mall.po;

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
 * 话题表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_topic")
@ApiModel(value = "CmsTopicPo对象", description = "话题表")
public class CmsTopicPo extends Model<CmsTopicPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("category_id")
    private Long categoryId;

    @TableField("name")
    private String name;

    @TableField("create_time")
    private Date createTime;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty("参与人数")
    @TableField("attend_count")
    private Integer attendCount;

    @ApiModelProperty("关注人数")
    @TableField("attention_count")
    private Integer attentionCount;

    @TableField("read_count")
    private Integer readCount;

    @ApiModelProperty("奖品名称")
    @TableField("award_name")
    private String awardName;

    @ApiModelProperty("参与方式")
    @TableField("attend_type")
    private String attendType;

    @ApiModelProperty("话题内容")
    @TableField("content")
    private String content;


    public static final String ID = "id";

    public static final String CATEGORY_ID = "category_id";

    public static final String NAME = "name";

    public static final String CREATE_TIME = "create_time";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String ATTEND_COUNT = "attend_count";

    public static final String ATTENTION_COUNT = "attention_count";

    public static final String READ_COUNT = "read_count";

    public static final String AWARD_NAME = "award_name";

    public static final String ATTEND_TYPE = "attend_type";

    public static final String CONTENT = "content";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
