package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.dao.ProductDao;
import com.niu.mall.admin.dto.ProductParam;
import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.model.ProductBasic;
import com.niu.mall.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public int create(ProductParam product) {

        productDao.create(product);
        return 0;
    }

    @Override
    public int update(Long id, ProductParam product) {
        productDao.update(id,product);
        return 0;
    }

    @Override
    public Product getByProductId(Long productId) {
        Product product=productDao.findById(productId);
        return product;
    }

    @Override
    public List<ProductParam> list(ProductParam productParam, Integer pageSize, Integer pageNum) {
        List<ProductBasic> list=productParam.getProductBasic();
        return null;
    }
}

