package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.dao.ProductMapper;
import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public int updateAttributeById(ProductAttribute  productAttribute) {

        productMapper.updateAttributeById(productAttribute.getProductId(), productAttribute);
        return 0;
    }

    @Override
    public ProductAttribute findAttributeById(Long id) {
        productMapper.findAttributeById(id);
        return null;
    }

}
