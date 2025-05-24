package com.niu.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 客户端启动类
 *
 * @author lihaojie
 * @date 2025/05/21 19:36
 **/
@SpringBootApplication(scanBasePackages = {"com.niu.mall"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
