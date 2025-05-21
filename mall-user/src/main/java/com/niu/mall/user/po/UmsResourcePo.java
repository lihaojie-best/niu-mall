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
import java.util.Date;

/**
 * <p>
 * 后台资源表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Data
@Accessors(chain = true)
@TableName("ums_resource")
@ApiModel(value = "UmsResourcePo对象", description = "后台资源表")
public class UmsResourcePo  extends Model<UmsResourcePo> implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("资源名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("资源URL")
    @TableField("url")
    private String url;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("资源分类ID")
    @TableField("category_id")
    private Long categoryId;


    public static final String ID = "id";

    public static final String CREATE_TIME = "create_time";

    public static final String NAME = "name";

    public static final String URL = "url";

    public static final String DESCRIPTION = "description";

    public static final String CATEGORY_ID = "category_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
