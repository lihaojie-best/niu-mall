package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 存储产品参数信息的表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("pms_product_attribute_value")
@ApiModel(value = "PmsProductAttributeValuePo对象", description = "存储产品参数信息的表")
public class PmsProductAttributeValuePo extends Model<PmsProductAttributeValuePo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("product_attribute_id")
    private Long productAttributeId;

    @ApiModelProperty("手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    @TableField("value")
    private String value;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_ATTRIBUTE_ID = "product_attribute_id";

    public static final String VALUE = "value";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
