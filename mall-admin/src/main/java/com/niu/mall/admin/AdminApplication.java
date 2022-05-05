package com.niu.mall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

/**
 * mall-admin启动类
 *
 * @author Abel ZhaoKun
 * @date 2022/4/11 9:03
 */
@SpringBootApplication
@MapperScan("com.niu.mall.admin.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
