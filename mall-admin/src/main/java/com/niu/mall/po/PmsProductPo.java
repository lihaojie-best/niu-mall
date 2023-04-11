package com.niu.mall.po;

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
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("pms_product")
@ApiModel(value = "PmsProductPo对象", description = "商品信息")
public class PmsProductPo extends Model<PmsProductPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("brand_id")
    private Long brandId;

    @TableField("product_category_id")
    private Long productCategoryId;

    @TableField("feight_template_id")
    private Long feightTemplateId;

    @TableField("product_attribute_category_id")
    private Long productAttributeCategoryId;

    @TableField("name")
    private String name;

    @TableField("pic")
    private String pic;

    @ApiModelProperty("货号")
    @TableField("product_sn")
    private String productSn;

    @ApiModelProperty("删除状态：0->未删除；1->已删除")
    @TableField("delete_status")
    private Integer deleteStatus;

    @ApiModelProperty("上架状态：0->下架；1->上架")
    @TableField("publish_status")
    private Integer publishStatus;

    @ApiModelProperty("新品状态:0->不是新品；1->新品")
    @TableField("new_status")
    private Integer newStatus;

    @ApiModelProperty("推荐状态；0->不推荐；1->推荐")
    @TableField("recommand_status")
    private Integer recommandStatus;

    @ApiModelProperty("审核状态：0->未审核；1->审核通过")
    @TableField("verify_status")
    private Integer verifyStatus;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("销量")
    @TableField("sale")
    private Integer sale;

    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("促销价格")
    @TableField("promotion_price")
    private BigDecimal promotionPrice;

    @ApiModelProperty("赠送的成长值")
    @TableField("gift_growth")
    private Integer giftGrowth;

    @ApiModelProperty("赠送的积分")
    @TableField("gift_point")
    private Integer giftPoint;

    @ApiModelProperty("限制使用的积分数")
    @TableField("use_point_limit")
    private Integer usePointLimit;

    @ApiModelProperty("副标题")
    @TableField("sub_title")
    private String subTitle;

    @ApiModelProperty("商品描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("市场价")
    @TableField("original_price")
    private BigDecimal originalPrice;

    @ApiModelProperty("库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty("库存预警值")
    @TableField("low_stock")
    private Integer lowStock;

    @ApiModelProperty("单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("商品重量，默认为克")
    @TableField("weight")
    private BigDecimal weight;

    @ApiModelProperty("是否为预告商品：0->不是；1->是")
    @TableField("preview_status")
    private Integer previewStatus;

    @ApiModelProperty("以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    @TableField("service_ids")
    private String serviceIds;

    @TableField("keywords")
    private String keywords;

    @TableField("note")
    private String note;

    @ApiModelProperty("画册图片，连产品图片限制为5张，以逗号分割")
    @TableField("album_pics")
    private String albumPics;

    @TableField("detail_title")
    private String detailTitle;

    @TableField("detail_desc")
    private String detailDesc;

    @ApiModelProperty("产品详情网页内容")
    @TableField("detail_html")
    private String detailHtml;

    @ApiModelProperty("移动端网页详情")
    @TableField("detail_mobile_html")
    private String detailMobileHtml;

    @ApiModelProperty("促销开始时间")
    @TableField("promotion_start_time")
    private Date promotionStartTime;

    @ApiModelProperty("促销结束时间")
    @TableField("promotion_end_time")
    private Date promotionEndTime;

    @ApiModelProperty("活动限购数量")
    @TableField("promotion_per_limit")
    private Integer promotionPerLimit;

    @ApiModelProperty("促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    @TableField("promotion_type")
    private Integer promotionType;

    @ApiModelProperty("品牌名称")
    @TableField("brand_name")
    private String brandName;

    @ApiModelProperty("商品分类名称")
    @TableField("product_category_name")
    private String productCategoryName;


    public static final String ID = "id";

    public static final String BRAND_ID = "brand_id";

    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    public static final String FEIGHT_TEMPLATE_ID = "feight_template_id";

    public static final String PRODUCT_ATTRIBUTE_CATEGORY_ID = "product_attribute_category_id";

    public static final String NAME = "name";

    public static final String PIC = "pic";

    public static final String PRODUCT_SN = "product_sn";

    public static final String DELETE_STATUS = "delete_status";

    public static final String PUBLISH_STATUS = "publish_status";

    public static final String NEW_STATUS = "new_status";

    public static final String RECOMMAND_STATUS = "recommand_status";

    public static final String VERIFY_STATUS = "verify_status";

    public static final String SORT = "sort";

    public static final String SALE = "sale";

    public static final String PRICE = "price";

    public static final String PROMOTION_PRICE = "promotion_price";

    public static final String GIFT_GROWTH = "gift_growth";

    public static final String GIFT_POINT = "gift_point";

    public static final String USE_POINT_LIMIT = "use_point_limit";

    public static final String SUB_TITLE = "sub_title";

    public static final String DESCRIPTION = "description";

    public static final String ORIGINAL_PRICE = "original_price";

    public static final String STOCK = "stock";

    public static final String LOW_STOCK = "low_stock";

    public static final String UNIT = "unit";

    public static final String WEIGHT = "weight";

    public static final String PREVIEW_STATUS = "preview_status";

    public static final String SERVICE_IDS = "service_ids";

    public static final String KEYWORDS = "keywords";

    public static final String NOTE = "note";

    public static final String ALBUM_PICS = "album_pics";

    public static final String DETAIL_TITLE = "detail_title";

    public static final String DETAIL_DESC = "detail_desc";

    public static final String DETAIL_HTML = "detail_html";

    public static final String DETAIL_MOBILE_HTML = "detail_mobile_html";

    public static final String PROMOTION_START_TIME = "promotion_start_time";

    public static final String PROMOTION_END_TIME = "promotion_end_time";

    public static final String PROMOTION_PER_LIMIT = "promotion_per_limit";

    public static final String PROMOTION_TYPE = "promotion_type";

    public static final String BRAND_NAME = "brand_name";

    public static final String PRODUCT_CATEGORY_NAME = "product_category_name";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
