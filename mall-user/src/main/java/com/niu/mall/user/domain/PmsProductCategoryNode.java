package com.niu.mall.user.domain;


import com.niu.mall.user.po.PmsProductCategoryPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含子分类的商品分类
 * Created by lihaojie on 2023/4/6.
 */
@Getter
@Setter
public class PmsProductCategoryNode extends PmsProductCategoryPo {
    @ApiModelProperty("子分类集合")
    private List<PmsProductCategoryNode> children;
}
