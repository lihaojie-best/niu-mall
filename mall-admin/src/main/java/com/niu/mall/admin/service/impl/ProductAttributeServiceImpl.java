package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.dao.ProductDao;
import com.niu.mall.admin.dto.ProductAttributeParam;
import com.niu.mall.admin.dto.ProductParam;
import com.niu.mall.admin.model.ProductAttribute;
import com.niu.mall.admin.service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    @Autowired
    private ProductDao productDao;
    @Override
    public int updateAttributeById(ProductAttribute productAttribute) {

        productDao.updateAttributeById(productAttribute.getProductId(), productAttribute);
        return 0;
    }

    @Override
    public ProductAttribute getItem(Long id) {
        productDao.findAttributeById(id);
        return null;
    }

}
