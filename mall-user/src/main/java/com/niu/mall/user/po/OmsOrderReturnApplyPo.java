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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单退货申请
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oms_order_return_apply")
@ApiModel(value = "OmsOrderReturnApplyPo对象", description = "订单退货申请")
public class OmsOrderReturnApplyPo extends Model<OmsOrderReturnApplyPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("收货地址表id")
    @TableField("company_address_id")
    private Long companyAddressId;

    @ApiModelProperty("退货商品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("订单编号")
    @TableField("order_sn")
    private String orderSn;

    @ApiModelProperty("申请时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("会员用户名")
    @TableField("member_username")
    private String memberUsername;

    @ApiModelProperty("退款金额")
    @TableField("return_amount")
    private BigDecimal returnAmount;

    @ApiModelProperty("退货人姓名")
    @TableField("return_name")
    private String returnName;

    @ApiModelProperty("退货人电话")
    @TableField("return_phone")
    private String returnPhone;

    @ApiModelProperty("申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("处理时间")
    @TableField("handle_time")
    private Date handleTime;

    @ApiModelProperty("商品图片")
    @TableField("product_pic")
    private String productPic;

    @ApiModelProperty("商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("商品品牌")
    @TableField("product_brand")
    private String productBrand;

    @ApiModelProperty("商品销售属性：颜色：红色；尺码：xl;")
    @TableField("product_attr")
    private String productAttr;

    @ApiModelProperty("退货数量")
    @TableField("product_count")
    private Integer productCount;

    @ApiModelProperty("商品单价")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty("商品实际支付单价")
    @TableField("product_real_price")
    private BigDecimal productRealPrice;

    @ApiModelProperty("原因")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("凭证图片，以逗号隔开")
    @TableField("proof_pics")
    private String proofPics;

    @ApiModelProperty("处理备注")
    @TableField("handle_note")
    private String handleNote;

    @ApiModelProperty("处理人员")
    @TableField("handle_man")
    private String handleMan;

    @ApiModelProperty("收货人")
    @TableField("receive_man")
    private String receiveMan;

    @ApiModelProperty("收货时间")
    @TableField("receive_time")
    private Date receiveTime;

    @ApiModelProperty("收货备注")
    @TableField("receive_note")
    private String receiveNote;


    public static final String ID = "id";

    public static final String ORDER_ID = "order_id";

    public static final String COMPANY_ADDRESS_ID = "company_address_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String ORDER_SN = "order_sn";

    public static final String CREATE_TIME = "create_time";

    public static final String MEMBER_USERNAME = "member_username";

    public static final String RETURN_AMOUNT = "return_amount";

    public static final String RETURN_NAME = "return_name";

    public static final String RETURN_PHONE = "return_phone";

    public static final String STATUS = "status";

    public static final String HANDLE_TIME = "handle_time";

    public static final String PRODUCT_PIC = "product_pic";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_BRAND = "product_brand";

    public static final String PRODUCT_ATTR = "product_attr";

    public static final String PRODUCT_COUNT = "product_count";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_REAL_PRICE = "product_real_price";

    public static final String REASON = "reason";

    public static final String DESCRIPTION = "description";

    public static final String PROOF_PICS = "proof_pics";

    public static final String HANDLE_NOTE = "handle_note";

    public static final String HANDLE_MAN = "handle_man";

    public static final String RECEIVE_MAN = "receive_man";

    public static final String RECEIVE_TIME = "receive_time";

    public static final String RECEIVE_NOTE = "receive_note";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
