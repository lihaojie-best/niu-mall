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
 * 商品审核记录
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_product_vertify_record")
@ApiModel(value = "PmsProductVertifyRecordPo对象", description = "商品审核记录")
public class PmsProductVertifyRecordPo extends Model<PmsProductVertifyRecordPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("审核人")
    @TableField("vertify_man")
    private String vertifyMan;

    @TableField("status")
    private Integer status;

    @ApiModelProperty("反馈详情")
    @TableField("detail")
    private String detail;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String CREATE_TIME = "create_time";

    public static final String VERTIFY_MAN = "vertify_man";

    public static final String STATUS = "status";

    public static final String DETAIL = "detail";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
