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
 * 成长值变化历史记录表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ums_growth_change_history")
@ApiModel(value = "UmsGrowthChangeHistoryPo对象", description = "成长值变化历史记录表")
public class UmsGrowthChangeHistoryPo extends Model<UmsGrowthChangeHistoryPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("改变类型：0->增加；1->减少")
    @TableField("change_type")
    private Integer changeType;

    @ApiModelProperty("积分改变数量")
    @TableField("change_count")
    private Integer changeCount;

    @ApiModelProperty("操作人员")
    @TableField("operate_man")
    private String operateMan;

    @ApiModelProperty("操作备注")
    @TableField("operate_note")
    private String operateNote;

    @ApiModelProperty("积分来源：0->购物；1->管理员修改")
    @TableField("source_type")
    private Integer sourceType;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String CREATE_TIME = "create_time";

    public static final String CHANGE_TYPE = "change_type";

    public static final String CHANGE_COUNT = "change_count";

    public static final String OPERATE_MAN = "operate_man";

    public static final String OPERATE_NOTE = "operate_note";

    public static final String SOURCE_TYPE = "source_type";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
