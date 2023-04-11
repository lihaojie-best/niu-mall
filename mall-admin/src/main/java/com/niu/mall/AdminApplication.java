package com.niu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * mall-admin启动类
 *
 * @author Abel ZhaoKun
 * @date 2022/4/11 9:03
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.niu.mall.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
