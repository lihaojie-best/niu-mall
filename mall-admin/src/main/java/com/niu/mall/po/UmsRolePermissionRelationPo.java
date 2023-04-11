package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户角色和权限关系表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_role_permission_relation")
@ApiModel(value = "UmsRolePermissionRelationPo对象", description = "后台用户角色和权限关系表")
public class UmsRolePermissionRelationPo extends Model<UmsRolePermissionRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("permission_id")
    private Long permissionId;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
