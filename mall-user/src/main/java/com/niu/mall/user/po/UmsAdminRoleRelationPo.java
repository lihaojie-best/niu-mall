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
 * 后台用户和角色关系表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_admin_role_relation")
@ApiModel(value = "UmsAdminRoleRelationPo对象", description = "后台用户和角色关系表")
public class UmsAdminRoleRelationPo extends Model<UmsAdminRoleRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("admin_id")
    private Long adminId;

    @TableField("role_id")
    private Long roleId;


    public static final String ID = "id";

    public static final String ADMIN_ID = "admin_id";

    public static final String ROLE_ID = "role_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
