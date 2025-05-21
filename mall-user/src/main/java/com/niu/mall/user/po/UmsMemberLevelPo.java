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

/**
 * <p>
 * 会员等级表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_level")
@ApiModel(value = "UmsMemberLevelPo对象", description = "会员等级表")
public class UmsMemberLevelPo extends Model<UmsMemberLevelPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("growth_point")
    private Integer growthPoint;

    @ApiModelProperty("是否为默认等级：0->不是；1->是")
    @TableField("default_status")
    private Integer defaultStatus;

    @ApiModelProperty("免运费标准")
    @TableField("free_freight_point")
    private BigDecimal freeFreightPoint;

    @ApiModelProperty("每次评价获取的成长值")
    @TableField("comment_growth_point")
    private Integer commentGrowthPoint;

    @ApiModelProperty("是否有免邮特权")
    @TableField("priviledge_free_freight")
    private Integer priviledgeFreeFreight;

    @ApiModelProperty("是否有签到特权")
    @TableField("priviledge_sign_in")
    private Integer priviledgeSignIn;

    @ApiModelProperty("是否有评论获奖励特权")
    @TableField("priviledge_comment")
    private Integer priviledgeComment;

    @ApiModelProperty("是否有专享活动特权")
    @TableField("priviledge_promotion")
    private Integer priviledgePromotion;

    @ApiModelProperty("是否有会员价格特权")
    @TableField("priviledge_member_price")
    private Integer priviledgeMemberPrice;

    @ApiModelProperty("是否有生日特权")
    @TableField("priviledge_birthday")
    private Integer priviledgeBirthday;

    @TableField("note")
    private String note;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String GROWTH_POINT = "growth_point";

    public static final String DEFAULT_STATUS = "default_status";

    public static final String FREE_FREIGHT_POINT = "free_freight_point";

    public static final String COMMENT_GROWTH_POINT = "comment_growth_point";

    public static final String PRIVILEDGE_FREE_FREIGHT = "priviledge_free_freight";

    public static final String PRIVILEDGE_SIGN_IN = "priviledge_sign_in";

    public static final String PRIVILEDGE_COMMENT = "priviledge_comment";

    public static final String PRIVILEDGE_PROMOTION = "priviledge_promotion";

    public static final String PRIVILEDGE_MEMBER_PRICE = "priviledge_member_price";

    public static final String PRIVILEDGE_BIRTHDAY = "priviledge_birthday";

    public static final String NOTE = "note";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
