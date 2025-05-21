package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Data
@Accessors(chain = true)
@TableName("pms_product_category")
@ApiModel(value = "PmsProductCategoryPo对象", description = "产品分类")
public class PmsProductCategoryPo extends Model<PmsProductCategoryPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("上机分类的编号：0表示一级分类")
    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    @TableField("level")
    private Integer level;

    @TableField("product_count")
    private Integer productCount;

    @TableField("product_unit")
    private String productUnit;

    @ApiModelProperty("是否显示在导航栏：0->不显示；1->显示")
    @TableField("nav_status")
    private Integer navStatus;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    @TableField("show_status")
    private Integer showStatus;

    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @TableField("keywords")
    private String keywords;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;


    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String LEVEL = "level";

    public static final String PRODUCT_COUNT = "product_count";

    public static final String PRODUCT_UNIT = "product_unit";

    public static final String NAV_STATUS = "nav_status";

    public static final String SHOW_STATUS = "show_status";

    public static final String SORT = "sort";

    public static final String ICON = "icon";

    public static final String KEYWORDS = "keywords";

    public static final String DESCRIPTION = "description";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
