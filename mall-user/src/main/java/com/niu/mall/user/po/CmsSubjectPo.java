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
 * 专题表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_subject")
@ApiModel(value = "CmsSubjectPo对象", description = "专题表")
public class CmsSubjectPo extends Model<CmsSubjectPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("category_id")
    private Long categoryId;

    @TableField("title")
    private String title;

    @ApiModelProperty("专题主图")
    @TableField("pic")
    private String pic;

    @ApiModelProperty("关联产品数量")
    @TableField("product_count")
    private Integer productCount;

    @TableField("recommend_status")
    private Integer recommendStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("collect_count")
    private Integer collectCount;

    @TableField("read_count")
    private Integer readCount;

    @TableField("comment_count")
    private Integer commentCount;

    @ApiModelProperty("画册图片用逗号分割")
    @TableField("album_pics")
    private String albumPics;

    @TableField("description")
    private String description;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    @TableField("show_status")
    private Integer showStatus;

    @TableField("content")
    private String content;

    @ApiModelProperty("转发数")
    @TableField("forward_count")
    private Integer forwardCount;

    @ApiModelProperty("专题分类名称")
    @TableField("category_name")
    private String categoryName;


    public static final String ID = "id";

    public static final String CATEGORY_ID = "category_id";

    public static final String TITLE = "title";

    public static final String PIC = "pic";

    public static final String PRODUCT_COUNT = "product_count";

    public static final String RECOMMEND_STATUS = "recommend_status";

    public static final String CREATE_TIME = "create_time";

    public static final String COLLECT_COUNT = "collect_count";

    public static final String READ_COUNT = "read_count";

    public static final String COMMENT_COUNT = "comment_count";

    public static final String ALBUM_PICS = "album_pics";

    public static final String DESCRIPTION = "description";

    public static final String SHOW_STATUS = "show_status";

    public static final String CONTENT = "content";

    public static final String FORWARD_COUNT = "forward_count";

    public static final String CATEGORY_NAME = "category_name";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
