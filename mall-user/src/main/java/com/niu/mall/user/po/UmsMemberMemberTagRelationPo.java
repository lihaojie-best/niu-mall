package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户和标签关系表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_member_tag_relation")
@ApiModel(value = "UmsMemberMemberTagRelationPo对象", description = "用户和标签关系表")
public class UmsMemberMemberTagRelationPo extends Model<UmsMemberMemberTagRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("tag_id")
    private Long tagId;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String TAG_ID = "tag_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
