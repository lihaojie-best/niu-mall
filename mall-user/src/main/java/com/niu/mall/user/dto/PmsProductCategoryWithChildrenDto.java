package com.niu.mall.user.dto;

import com.niu.mall.user.po.PmsProductCategoryPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 查询一级分类及子类Dto
 *
 * @author lihaojie
 * @date 2022/12/19 11:06
 **/
@Data
public class PmsProductCategoryWithChildrenDto extends PmsProductCategoryPo {
    @ApiModelProperty("子分类")
    private List<PmsProductCategoryPo> childrenProductCategoryPoList;
}
