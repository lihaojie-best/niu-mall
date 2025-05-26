package com.niu.mall.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * Created by lihaojie on 2023/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.niu.mall.user.dao")
public class MyBatisConfig {
}
