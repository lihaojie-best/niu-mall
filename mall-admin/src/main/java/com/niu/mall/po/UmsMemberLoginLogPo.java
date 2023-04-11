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
 * 会员登录记录
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_login_log")
@ApiModel(value = "UmsMemberLoginLogPo对象", description = "会员登录记录")
public class UmsMemberLoginLogPo extends Model<UmsMemberLoginLogPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("create_time")
    private Date createTime;

    @TableField("ip")
    private String ip;

    @TableField("city")
    private String city;

    @ApiModelProperty("登录类型：0->PC；1->android;2->ios;3->小程序")
    @TableField("login_type")
    private Integer loginType;

    @TableField("province")
    private String province;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String CREATE_TIME = "create_time";

    public static final String IP = "ip";

    public static final String CITY = "city";

    public static final String LOGIN_TYPE = "login_type";

    public static final String PROVINCE = "province";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
