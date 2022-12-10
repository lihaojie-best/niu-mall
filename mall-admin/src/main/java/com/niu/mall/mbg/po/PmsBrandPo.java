package com.niu.mall.mbg.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("pms_brand")
@ApiModel(value = "PmsBrandPo对象", description = "品牌表")
public class PmsBrandPo extends Model<PmsBrandPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @ApiModelProperty("首字母")
    @TableField("first_letter")
    private String firstLetter;

    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("是否为品牌制造商：0->不是；1->是")
    @TableField("factory_status")
    private Integer factoryStatus;

    @TableField("show_status")
    private Integer showStatus;

    @ApiModelProperty("产品数量")
    @TableField("product_count")
    private Integer productCount;

    @ApiModelProperty("产品评论数量")
    @TableField("product_comment_count")
    private Integer productCommentCount;

    @ApiModelProperty("品牌logo")
    @TableField("logo")
    private String logo;

    @ApiModelProperty("专区大图")
    @TableField("big_pic")
    private String bigPic;

    @ApiModelProperty("品牌故事")
    @TableField("brand_story")
    private String brandStory;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String FIRST_LETTER = "first_letter";

    public static final String SORT = "sort";

    public static final String FACTORY_STATUS = "factory_status";

    public static final String SHOW_STATUS = "show_status";

    public static final String PRODUCT_COUNT = "product_count";

    public static final String PRODUCT_COMMENT_COUNT = "product_comment_count";

    public static final String LOGO = "logo";

    public static final String BIG_PIC = "big_pic";

    public static final String BRAND_STORY = "brand_story";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
