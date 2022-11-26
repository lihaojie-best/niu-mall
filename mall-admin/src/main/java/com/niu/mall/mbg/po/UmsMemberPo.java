package com.niu.mall.mbg.po;

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
 * 会员表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member")
@ApiModel(value = "UmsMemberPo对象", description = "会员表")
public class UmsMemberPo extends Model<UmsMemberPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_level_id")
    private Long memberLevelId;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("帐号启用状态:0->禁用；1->启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("注册时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("头像")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty("所做城市")
    @TableField("city")
    private String city;

    @ApiModelProperty("职业")
    @TableField("job")
    private String job;

    @ApiModelProperty("个性签名")
    @TableField("personalized_signature")
    private String personalizedSignature;

    @ApiModelProperty("用户来源")
    @TableField("source_type")
    private Integer sourceType;

    @ApiModelProperty("积分")
    @TableField("integration")
    private Integer integration;

    @ApiModelProperty("成长值")
    @TableField("growth")
    private Integer growth;

    @ApiModelProperty("剩余抽奖次数")
    @TableField("luckey_count")
    private Integer luckeyCount;

    @ApiModelProperty("历史积分数量")
    @TableField("history_integration")
    private Integer historyIntegration;


    public static final String ID = "id";

    public static final String MEMBER_LEVEL_ID = "member_level_id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String NICKNAME = "nickname";

    public static final String PHONE = "phone";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String ICON = "icon";

    public static final String GENDER = "gender";

    public static final String BIRTHDAY = "birthday";

    public static final String CITY = "city";

    public static final String JOB = "job";

    public static final String PERSONALIZED_SIGNATURE = "personalized_signature";

    public static final String SOURCE_TYPE = "source_type";

    public static final String INTEGRATION = "integration";

    public static final String GROWTH = "growth";

    public static final String LUCKEY_COUNT = "luckey_count";

    public static final String HISTORY_INTEGRATION = "history_integration";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
