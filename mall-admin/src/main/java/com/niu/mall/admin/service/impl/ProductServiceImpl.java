package com.niu.mall.admin.service.impl;

import com.niu.mall.common.api.Result;
import com.niu.mall.mbg.domain.ProductDomain;
import com.niu.mall.admin.dao.ProductDao;
import com.niu.mall.admin.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品基本信息 服务实现类
 * </p>
 *
 * @author lihaojie
 * @since 2022-08-31
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductDomain> implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Result createProduct(ProductDomain product) {
        product.setProductId(null);
        int count =productDao.createProduct(product);

        if (count > 0) {
            return Result.success(product.getProductId(),"商品创建成功");
        }
        return Result.failed("fail","商品创建失败");
    }

    @Override
    public Result update(ProductDomain product) {
        int count=productDao.update(product);
        if (count > 0) {
            return Result.success("success","更新成功");
        }
        return Result.failed("fail","更新失败");
    }

    @Override
    public Result getByProductId(Long id) {
        if (productDao.findById(id)==null) {
            System.out.println("空指针异常");
            return Result.failed("fail","查询失败");
        }
        ProductDomain product =productDao.findById(id);
        return Result.success(product,"查询成功");
    }
}
