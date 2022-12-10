package com.niu.mall.admin.dto;

import com.niu.mall.mbg.po.PmsProductAttributeCategoryPo;
import com.niu.mall.mbg.po.PmsProductAttributePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  带有属性的商品属性分类
 *
 * @author lihaojie
 * @date 2022/12/10 21:35
 **/
public class PmsProductAttributeCategoryDto extends PmsProductAttributeCategoryPo {
    @Getter
    @Setter
    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttributePo> productAttributeList;
}
