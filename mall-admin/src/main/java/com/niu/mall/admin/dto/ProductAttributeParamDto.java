package com.niu.mall.admin.dto;

import com.niu.mall.admin.model.ProductAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttributeParamDto  {
    private ProductAttribute productAttribute;


}
