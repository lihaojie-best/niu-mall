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
 * 后台用户登录日志表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_admin_login_log")
@ApiModel(value = "UmsAdminLoginLogPo对象", description = "后台用户登录日志表")
public class UmsAdminLoginLogPo extends Model<UmsAdminLoginLogPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("admin_id")
    private Long adminId;

    @TableField("create_time")
    private Date createTime;

    @TableField("ip")
    private String ip;

    @TableField("address")
    private String address;

    @ApiModelProperty("浏览器登录类型")
    @TableField("user_agent")
    private String userAgent;


    public static final String ID = "id";

    public static final String ADMIN_ID = "admin_id";

    public static final String CREATE_TIME = "create_time";

    public static final String IP = "ip";

    public static final String ADDRESS = "address";

    public static final String USER_AGENT = "user_agent";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
