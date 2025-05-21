package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 用户举报表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_member_report")
@ApiModel(value = "CmsMemberReportPo对象", description = "用户举报表")
public class CmsMemberReportPo extends Model<CmsMemberReportPo> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @ApiModelProperty("举报类型：0->商品评价；1->话题内容；2->用户评论")
    @TableField("report_type")
    private Integer reportType;

    @ApiModelProperty("举报人")
    @TableField("report_member_name")
    private String reportMemberName;

    @TableField("create_time")
    private Date createTime;

    @TableField("report_object")
    private String reportObject;

    @ApiModelProperty("举报状态：0->未处理；1->已处理")
    @TableField("report_status")
    private Integer reportStatus;

    @ApiModelProperty("处理结果：0->无效；1->有效；2->恶意")
    @TableField("handle_status")
    private Integer handleStatus;

    @TableField("note")
    private String note;


    public static final String ID = "id";

    public static final String REPORT_TYPE = "report_type";

    public static final String REPORT_MEMBER_NAME = "report_member_name";

    public static final String CREATE_TIME = "create_time";

    public static final String REPORT_OBJECT = "report_object";

    public static final String REPORT_STATUS = "report_status";

    public static final String HANDLE_STATUS = "handle_status";

    public static final String NOTE = "note";

    @Override
    public Serializable pkVal() {
        return null;
    }

}
