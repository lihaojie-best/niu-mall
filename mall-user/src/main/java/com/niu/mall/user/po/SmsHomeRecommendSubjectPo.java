package com.niu.mall.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 首页推荐专题表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sms_home_recommend_subject")
@ApiModel(value = "SmsHomeRecommendSubjectPo对象", description = "首页推荐专题表")
public class SmsHomeRecommendSubjectPo extends Model<SmsHomeRecommendSubjectPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("subject_id")
    private Long subjectId;

    @TableField("subject_name")
    private String subjectName;

    @TableField("recommend_status")
    private Integer recommendStatus;

    @TableField("sort")
    private Integer sort;


    public static final String ID = "id";

    public static final String SUBJECT_ID = "subject_id";

    public static final String SUBJECT_NAME = "subject_name";

    public static final String RECOMMEND_STATUS = "recommend_status";

    public static final String SORT = "sort";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
