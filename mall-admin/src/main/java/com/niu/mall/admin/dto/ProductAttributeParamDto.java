package com.niu.mall.admin.dto;

import com.niu.mall.admin.model.ProductAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sun.security.krb5.internal.PAData;

import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttributeParamDto  {
    private ProductAttribute productAttribute;


}
