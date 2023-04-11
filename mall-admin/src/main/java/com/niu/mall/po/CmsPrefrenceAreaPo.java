package com.niu.mall.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优选专区
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_prefrence_area")
@ApiModel(value = "CmsPrefrenceAreaPo对象", description = "优选专区")
public class CmsPrefrenceAreaPo extends Model<CmsPrefrenceAreaPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("sub_title")
    private String subTitle;

    @ApiModelProperty("展示图片")
    @TableField("pic")
    private byte[] pic;

    @TableField("sort")
    private Integer sort;

    @TableField("show_status")
    private Integer showStatus;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String SUB_TITLE = "sub_title";

    public static final String PIC = "pic";

    public static final String SORT = "sort";

    public static final String SHOW_STATUS = "show_status";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
