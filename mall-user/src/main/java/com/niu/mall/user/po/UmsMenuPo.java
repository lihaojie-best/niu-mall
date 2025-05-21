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
import java.util.Date;

/**
 * <p>
 * 后台菜单表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_menu")
@ApiModel(value = "UmsMenuPo对象", description = "后台菜单表")
public class UmsMenuPo extends Model<UmsMenuPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("菜单名称")
    @TableField("title")
    private String title;

    @ApiModelProperty("菜单级数")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("菜单排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("前端名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("前端图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("前端隐藏")
    @TableField("hidden")
    private Integer hidden;


    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String CREATE_TIME = "create_time";

    public static final String TITLE = "title";

    public static final String LEVEL = "level";

    public static final String SORT = "sort";

    public static final String NAME = "name";

    public static final String ICON = "icon";

    public static final String HIDDEN = "hidden";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
