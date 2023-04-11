package com.niu.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author lihaojie
 * @date 2023/03/23 16:16
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8761 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8761.class, args);
    }
}
