package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类）
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_product_category_relation")
@ApiModel(value = "UmsMemberProductCategoryRelationPo对象", description = "会员与产品分类关系表（用户喜欢的分类）")
public class UmsMemberProductCategoryRelationPo extends Model<UmsMemberProductCategoryRelationPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("product_category_id")
    private Long productCategoryId;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
