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
 * 产品评价回复表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_comment_replay")
@ApiModel(value = "PmsCommentReplayPo对象", description = "产品评价回复表")
public class PmsCommentReplayPo extends Model<PmsCommentReplayPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("comment_id")
    private Long commentId;

    @TableField("member_nick_name")
    private String memberNickName;

    @TableField("member_icon")
    private String memberIcon;

    @TableField("content")
    private String content;

    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("评论人员类型；0->会员；1->管理员")
    @TableField("type")
    private Integer type;


    public static final String ID = "id";

    public static final String COMMENT_ID = "comment_id";

    public static final String MEMBER_NICK_NAME = "member_nick_name";

    public static final String MEMBER_ICON = "member_icon";

    public static final String CONTENT = "content";

    public static final String CREATE_TIME = "create_time";

    public static final String TYPE = "type";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
