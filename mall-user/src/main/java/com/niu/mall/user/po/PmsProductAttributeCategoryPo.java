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
 * 产品属性分类表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Data
@Accessors(chain = true)
@TableName("pms_product_attribute_category")
@ApiModel(value = "PmsProductAttributeCategoryPo对象", description = "产品属性分类表")
public class PmsProductAttributeCategoryPo extends Model<PmsProductAttributeCategoryPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    public String getName() {
        return name;
    }

    @ApiModelProperty("属性数量")
    @TableField("attribute_count")
    private Integer attributeCount;

    @ApiModelProperty("参数数量")
    @TableField("param_count")
    private Integer paramCount;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ATTRIBUTE_COUNT = "attribute_count";

    public static final String PARAM_COUNT = "param_count";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
