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

/**
 * <p>
 * 会员收货地址表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_receive_address")
@ApiModel(value = "UmsMemberReceiveAddressPo对象", description = "会员收货地址表")
public class UmsMemberReceiveAddressPo extends Model<UmsMemberReceiveAddressPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @ApiModelProperty("收货人名称")
    @TableField("name")
    private String name;

    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty("是否为默认")
    @TableField("default_status")
    private Integer defaultStatus;

    @ApiModelProperty("邮政编码")
    @TableField("post_code")
    private String postCode;

    @ApiModelProperty("省份/直辖市")
    @TableField("province")
    private String province;

    @ApiModelProperty("城市")
    @TableField("city")
    private String city;

    @ApiModelProperty("区")
    @TableField("region")
    private String region;

    @ApiModelProperty("详细地址(街道)")
    @TableField("detail_address")
    private String detailAddress;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String NAME = "name";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String DEFAULT_STATUS = "default_status";

    public static final String POST_CODE = "post_code";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String REGION = "region";

    public static final String DETAIL_ADDRESS = "detail_address";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
