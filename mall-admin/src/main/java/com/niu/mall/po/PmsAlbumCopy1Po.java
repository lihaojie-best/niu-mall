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
 * 相册表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_album_copy1")
@ApiModel(value = "PmsAlbumCopy1Po对象", description = "相册表")
public class PmsAlbumCopy1Po extends Model<PmsAlbumCopy1Po> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("cover_pic")
    private String coverPic;

    @TableField("pic_count")
    private Integer picCount;

    @TableField("sort")
    private Integer sort;

    @TableField("description")
    private String description;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String COVER_PIC = "cover_pic";

    public static final String PIC_COUNT = "pic_count";

    public static final String SORT = "sort";

    public static final String DESCRIPTION = "description";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
