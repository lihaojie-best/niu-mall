package com.niu.mall.mbg.po;

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
 * 商品属性参数表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_product_attribute")
@ApiModel(value = "PmsProductAttributePo对象", description = "商品属性参数表")
public class PmsProductAttributePo extends Model<PmsProductAttributePo> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_attribute_category_id")
    private Long productAttributeCategoryId;

    @TableField("name")
    private String name;

    @ApiModelProperty("属性选择类型：0->唯一；1->单选；2->多选")
    @TableField("select_type")
    private Integer selectType;

    @ApiModelProperty("属性录入方式：0->手工录入；1->从列表中选取")
    @TableField("input_type")
    private Integer inputType;

    @ApiModelProperty("可选值列表，以逗号隔开")
    @TableField("input_list")
    private String inputList;

    @ApiModelProperty("排序字段：最高的可以单独上传图片")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("分类筛选样式：1->普通；1->颜色")
    @TableField("filter_type")
    private Integer filterType;

    @ApiModelProperty("检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @TableField("search_type")
    private Integer searchType;

    @ApiModelProperty("相同属性产品是否关联；0->不关联；1->关联")
    @TableField("related_status")
    private Integer relatedStatus;

    @ApiModelProperty("是否支持手动新增；0->不支持；1->支持")
    @TableField("hand_add_status")
    private Integer handAddStatus;

    @ApiModelProperty("属性的类型；0->规格；1->参数")
    @TableField("type")
    private Integer type;


    public static final String ID = "id";

    public static final String PRODUCT_ATTRIBUTE_CATEGORY_ID = "product_attribute_category_id";

    public static final String NAME = "name";

    public static final String SELECT_TYPE = "select_type";

    public static final String INPUT_TYPE = "input_type";

    public static final String INPUT_LIST = "input_list";

    public static final String SORT = "sort";

    public static final String FILTER_TYPE = "filter_type";

    public static final String SEARCH_TYPE = "search_type";

    public static final String RELATED_STATUS = "related_status";

    public static final String HAND_ADD_STATUS = "hand_add_status";

    public static final String TYPE = "type";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
