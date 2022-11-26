package com.niu.mall.mbg.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品限时购与商品关系表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_flash_promotion_product_relation")
@ApiModel(value = "SmsFlashPromotionProductRelationPo对象", description = "商品限时购与商品关系表")
public class SmsFlashPromotionProductRelationPo extends Model<SmsFlashPromotionProductRelationPo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("flash_promotion_id")
    private Long flashPromotionId;

    @ApiModelProperty("编号")
    @TableField("flash_promotion_session_id")
    private Long flashPromotionSessionId;

    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("限时购价格")
    @TableField("flash_promotion_price")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty("限时购数量")
    @TableField("flash_promotion_count")
    private Integer flashPromotionCount;

    @ApiModelProperty("每人限购数量")
    @TableField("flash_promotion_limit")
    private Integer flashPromotionLimit;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;


    public static final String ID = "id";

    public static final String FLASH_PROMOTION_ID = "flash_promotion_id";

    public static final String FLASH_PROMOTION_SESSION_ID = "flash_promotion_session_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String FLASH_PROMOTION_PRICE = "flash_promotion_price";

    public static final String FLASH_PROMOTION_COUNT = "flash_promotion_count";

    public static final String FLASH_PROMOTION_LIMIT = "flash_promotion_limit";

    public static final String SORT = "sort";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
