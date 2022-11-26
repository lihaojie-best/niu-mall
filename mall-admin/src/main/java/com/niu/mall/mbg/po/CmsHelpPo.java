package com.niu.mall.mbg.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帮助表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_help")
@ApiModel(value = "CmsHelpPo对象", description = "帮助表")
public class CmsHelpPo extends Model<CmsHelpPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("category_id")
    private Long categoryId;

    @TableField("icon")
    private String icon;

    @TableField("title")
    private String title;

    @TableField("show_status")
    private Integer showStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("read_count")
    private Integer readCount;

    @TableField("content")
    private String content;


    public static final String ID = "id";

    public static final String CATEGORY_ID = "category_id";

    public static final String ICON = "icon";

    public static final String TITLE = "title";

    public static final String SHOW_STATUS = "show_status";

    public static final String CREATE_TIME = "create_time";

    public static final String READ_COUNT = "read_count";

    public static final String CONTENT = "content";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
