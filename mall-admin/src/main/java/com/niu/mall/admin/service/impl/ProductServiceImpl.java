package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.dao.ProductMapper;
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
    private ProductMapper productMapper;
    @Override
    public int create(ProductParam product) {

        productMapper.create(product);
        return 0;
    }

    @Override
    public int update(Long id, ProductParam product) {
        productMapper.update(id,product);
        return 0;
    }

    @Override
    public Product getByProductId(Long productId) {
        Product product= productMapper.findById(productId);
        return product;
    }

    @Override
    public List<ProductParam> list(ProductParam productParam, Integer pageSize, Integer pageNum) {
        List<ProductBasic> list=productParam.getProductBasic();
        return null;
    }
}

