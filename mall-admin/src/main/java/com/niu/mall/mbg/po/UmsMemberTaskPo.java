package com.niu.mall.mbg.po;

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
 * 会员任务表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_member_task")
@ApiModel(value = "UmsMemberTaskPo对象", description = "会员任务表")
public class UmsMemberTaskPo extends Model<UmsMemberTaskPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @ApiModelProperty("赠送成长值")
    @TableField("growth")
    private Integer growth;

    @ApiModelProperty("赠送积分")
    @TableField("intergration")
    private Integer intergration;

    @ApiModelProperty("任务类型：0->新手任务；1->日常任务")
    @TableField("type")
    private Integer type;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String GROWTH = "growth";

    public static final String INTERGRATION = "intergration";

    public static final String TYPE = "type";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
