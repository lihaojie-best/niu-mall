package com.niu.mall.admin;

import com.niu.mall.admin.controller.ProductController;
import com.niu.mall.admin.dto.ProductParamDto;
import com.niu.mall.admin.model.Product;
import com.niu.mall.common.api.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)//测试启动器,加载springboot的测试注解FA
@SpringBootTest //标记测试类,并加载项目applicationContext上下文环境
public class AdminApplicationTests {
    @Autowired
    private ProductController productController;

    /**
     * 商品查询测试
     * */
    @Test
    public void getProduct(){

        Result<Product> product=productController.getByProductId(1001);
        System.out.println(product.toString());

    }


    /**
     * 商品创建测试类
     * */

    @Test
   public void createProduct() throws ParseException {
        //创建Product
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        //Product product=new Product(1L,"白色","手机",2000,"iphoneX",3999,200,"Apple",dateformat.parse("2016-6-19"));
        //将product封装入ProductParamDto
        ProductParamDto productParamDto=new ProductParamDto();
       // productParamDto.setProduct(product);
       // productController.create(product);

    }
}
