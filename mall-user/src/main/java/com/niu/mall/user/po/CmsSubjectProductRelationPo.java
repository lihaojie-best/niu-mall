package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 专题商品关系表
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
@TableName("cms_subject_product_relation")
@ApiModel(value = "CmsSubjectProductRelationPo对象", description = "专题商品关系表")
public class CmsSubjectProductRelationPo extends Model<CmsSubjectProductRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("subject_id")
    private Long subjectId;

    @TableField("product_id")
    private Long productId;


    public static final String ID = "id";

    public static final String SUBJECT_ID = "subject_id";

    public static final String PRODUCT_ID = "product_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
