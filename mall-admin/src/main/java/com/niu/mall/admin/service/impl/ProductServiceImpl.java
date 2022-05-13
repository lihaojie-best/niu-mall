package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.dao.ProductMapper;
import com.niu.mall.admin.dto.ProductParamDto;
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
    public int create(ProductParamDto productParamDto) {
        int count;
        Product product= productParamDto.getProduct();
        product.setProductId(null);
        productMapper.create(product);
        count = 1;
        return count;
    }

    @Override
    public int update(Long id, ProductParamDto productParam) {
        int count;
        //将id与ProductParamDto一并封装到Product类里
        Product product = productParam.getProduct();
        product.setProductId(id);
        productMapper.update(product);
        count = 1;
        return count;
    }

    @Override
    public Product getByProductId(Long productId) {
        Product product = productMapper.findById(productId);
        return product;
    }

    @Override
    public List<ProductParamDto> list(ProductParamDto productParamDto, Integer pageSize, Integer pageNum) {
       // List<ProductBasic> list = productParamDto.getProductBasic();
        return null;
    }
}

