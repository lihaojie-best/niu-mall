package com.niu.mall.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 * 
 * @author lihaojie
 * @date 2022/12/18 12:14
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductAttrInfoDto {
    @ApiModelProperty("商品属性ID")
    private Long attributeId;
    @ApiModelProperty("商品属性分类ID")
    private Long attributeCategoryId;
}
