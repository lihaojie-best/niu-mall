package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 帮助分类表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_help_category")
@ApiModel(value = "CmsHelpCategoryPo对象", description = "帮助分类表")
public class CmsHelpCategoryPo extends Model<CmsHelpCategoryPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @ApiModelProperty("分类图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("专题数量")
    @TableField("help_count")
    private Integer helpCount;

    @TableField("show_status")
    private Integer showStatus;

    @TableField("sort")
    private Integer sort;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ICON = "icon";

    public static final String HELP_COUNT = "help_count";

    public static final String SHOW_STATUS = "show_status";

    public static final String SORT = "sort";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
