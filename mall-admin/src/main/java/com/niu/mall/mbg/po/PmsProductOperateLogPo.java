package com.niu.mall.mbg.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_product_operate_log")
@ApiModel(value = "PmsProductOperateLogPo对象", description = "")
public class PmsProductOperateLogPo extends Model<PmsProductOperateLogPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("price_old")
    private BigDecimal priceOld;

    @TableField("price_new")
    private BigDecimal priceNew;

    @TableField("sale_price_old")
    private BigDecimal salePriceOld;

    @TableField("sale_price_new")
    private BigDecimal salePriceNew;

    @ApiModelProperty("赠送的积分")
    @TableField("gift_point_old")
    private Integer giftPointOld;

    @TableField("gift_point_new")
    private Integer giftPointNew;

    @TableField("use_point_limit_old")
    private Integer usePointLimitOld;

    @TableField("use_point_limit_new")
    private Integer usePointLimitNew;

    @ApiModelProperty("操作人")
    @TableField("operate_man")
    private String operateMan;

    @TableField("create_time")
    private Date createTime;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRICE_OLD = "price_old";

    public static final String PRICE_NEW = "price_new";

    public static final String SALE_PRICE_OLD = "sale_price_old";

    public static final String SALE_PRICE_NEW = "sale_price_new";

    public static final String GIFT_POINT_OLD = "gift_point_old";

    public static final String GIFT_POINT_NEW = "gift_point_new";

    public static final String USE_POINT_LIMIT_OLD = "use_point_limit_old";

    public static final String USE_POINT_LIMIT_NEW = "use_point_limit_new";

    public static final String OPERATE_MAN = "operate_man";

    public static final String CREATE_TIME = "create_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
