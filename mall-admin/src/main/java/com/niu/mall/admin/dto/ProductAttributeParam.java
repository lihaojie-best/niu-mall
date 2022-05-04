package com.niu.mall.admin.dto;

import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.model.ProductBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttributeParam {
    private ProductAttribute productAttribute;

}
