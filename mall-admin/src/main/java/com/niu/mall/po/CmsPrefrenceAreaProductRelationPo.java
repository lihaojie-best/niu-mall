package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优选专区和产品关系表
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
@TableName("cms_prefrence_area_product_relation")
@ApiModel(value = "CmsPrefrenceAreaProductRelationPo对象", description = "优选专区和产品关系表")
public class CmsPrefrenceAreaProductRelationPo extends Model<CmsPrefrenceAreaProductRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("prefrence_area_id")
    private Long prefrenceAreaId;

    @TableField("product_id")
    private Long productId;


    public static final String ID = "id";

    public static final String PREFRENCE_AREA_ID = "prefrence_area_id";

    public static final String PRODUCT_ID = "product_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
