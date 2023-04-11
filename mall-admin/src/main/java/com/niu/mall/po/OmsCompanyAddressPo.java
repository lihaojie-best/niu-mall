package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司收发货地址表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oms_company_address")
@ApiModel(value = "OmsCompanyAddressPo对象", description = "公司收发货地址表")
public class OmsCompanyAddressPo extends Model<OmsCompanyAddressPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("地址名称")
    @TableField("address_name")
    private String addressName;

    @ApiModelProperty("默认发货地址：0->否；1->是")
    @TableField("send_status")
    private Integer sendStatus;

    @ApiModelProperty("是否默认收货地址：0->否；1->是")
    @TableField("receive_status")
    private Integer receiveStatus;

    @ApiModelProperty("收发货人姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("收货人电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("省/直辖市")
    @TableField("province")
    private String province;

    @ApiModelProperty("市")
    @TableField("city")
    private String city;

    @ApiModelProperty("区")
    @TableField("region")
    private String region;

    @ApiModelProperty("详细地址")
    @TableField("detail_address")
    private String detailAddress;


    public static final String ID = "id";

    public static final String ADDRESS_NAME = "address_name";

    public static final String SEND_STATUS = "send_status";

    public static final String RECEIVE_STATUS = "receive_status";

    public static final String NAME = "name";

    public static final String PHONE = "phone";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String REGION = "region";

    public static final String DETAIL_ADDRESS = "detail_address";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
