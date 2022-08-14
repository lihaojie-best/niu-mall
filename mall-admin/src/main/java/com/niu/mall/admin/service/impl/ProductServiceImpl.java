package com.niu.mall.admin.service.impl;

import com.niu.mall.admin.domain.Product;
import com.niu.mall.admin.dao.ProductDao;
import com.niu.mall.admin.dto.ProductParamDto;
import com.niu.mall.admin.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niu.mall.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品基本信息 服务实现类
 * </p>
 *
 * @author lhj
 * @since 2022-07-03
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {
    //@Resource
    @Autowired
    private ProductDao productDao;

    @Override
    public Result createProduct(Product product) {

        product.setProductId(null);
        int count =productDao.createProduct(product);

        if (count > 0) {
            return Result.success(product.getProductId(),"商品创建成功");
        }
        return Result.failed("fail","商品创建失败");
    }

    @Override
    public Result update(Product product) {
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
        Product product =productDao.findById(id);
        return Result.success(product,"查询成功");
    }

    @Override
    public List<ProductParamDto> list(ProductParamDto productParamDto, Integer pageSize, Integer pageNum) {
        return null;
    }
}
