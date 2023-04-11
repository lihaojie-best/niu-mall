package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专题评论表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_topic_comment")
@ApiModel(value = "CmsTopicCommentPo对象", description = "专题评论表")
public class CmsTopicCommentPo extends Model<CmsTopicCommentPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_nick_name")
    private String memberNickName;

    @TableField("topic_id")
    private Long topicId;

    @TableField("member_icon")
    private String memberIcon;

    @TableField("content")
    private String content;

    @TableField("create_time")
    private Date createTime;

    @TableField("show_status")
    private Integer showStatus;


    public static final String ID = "id";

    public static final String MEMBER_NICK_NAME = "member_nick_name";

    public static final String TOPIC_ID = "topic_id";

    public static final String MEMBER_ICON = "member_icon";

    public static final String CONTENT = "content";

    public static final String CREATE_TIME = "create_time";

    public static final String SHOW_STATUS = "show_status";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
