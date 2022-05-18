package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.dao.ProductMapper;
import com.niu.mall.admin.dto.ProductParamDto;
import com.niu.mall.admin.model.Product;
import com.niu.mall.admin.service.ProductService;
import com.niu.mall.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    //@Resource
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result createProduct(Product product) {

        product.setProductId(null);
        int count =productMapper.createProduct(product);

        if (count > 0) {
            return Result.success(product.getProductId(),"商品创建成功");
        }
        return Result.failed("fail","商品创建失败");
    }

    @Override
    public Result update(Product product) {
        int count=productMapper.update(product);
        if (count > 0) {
            return Result.success("success","更新成功");
        }
        return Result.failed("fail","更新失败");
    }

    @Override
    public Result getByProductId(Long id) {
        Product product =new Product();
        productMapper.findById(id);
        /*if (product.getProductName()==null) {
            System.out.println("空指针异常");
            return Result.failed("fail","查询失败");
        }*/
        System.out.println(product.getProductName()+"22323441111");
          return Result.success(product,"查询成功");
    }

    @Override
    public List<ProductParamDto> list(ProductParamDto productParamDto, Integer pageSize, Integer pageNum) {
       // List<ProductBasic> list = productParamDto.getProductBasic();
        return null;
    }
}

