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
 * 商品评价表
 * </p>
 *
 * @author lihaojie
 * @since 2022-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pms_comment")
@ApiModel(value = "PmsCommentPo对象", description = "商品评价表")
public class PmsCommentPo extends Model<PmsCommentPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("member_nick_name")
    private String memberNickName;

    @TableField("product_name")
    private String productName;

    @ApiModelProperty("评价星数：0->5")
    @TableField("star")
    private Integer star;

    @ApiModelProperty("评价的ip")
    @TableField("member_ip")
    private String memberIp;

    @TableField("create_time")
    private Date createTime;

    @TableField("show_status")
    private Integer showStatus;

    @ApiModelProperty("购买时的商品属性")
    @TableField("product_attribute")
    private String productAttribute;

    @TableField("collect_couont")
    private Integer collectCouont;

    @TableField("read_count")
    private Integer readCount;

    @TableField("content")
    private String content;

    @ApiModelProperty("上传图片地址，以逗号隔开")
    @TableField("pics")
    private String pics;

    @ApiModelProperty("评论用户头像")
    @TableField("member_icon")
    private String memberIcon;

    @TableField("replay_count")
    private Integer replayCount;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String MEMBER_NICK_NAME = "member_nick_name";

    public static final String PRODUCT_NAME = "product_name";

    public static final String STAR = "star";

    public static final String MEMBER_IP = "member_ip";

    public static final String CREATE_TIME = "create_time";

    public static final String SHOW_STATUS = "show_status";

    public static final String PRODUCT_ATTRIBUTE = "product_attribute";

    public static final String COLLECT_COUONT = "collect_couont";

    public static final String READ_COUNT = "read_count";

    public static final String CONTENT = "content";

    public static final String PICS = "pics";

    public static final String MEMBER_ICON = "member_icon";

    public static final String REPLAY_COUNT = "replay_count";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
