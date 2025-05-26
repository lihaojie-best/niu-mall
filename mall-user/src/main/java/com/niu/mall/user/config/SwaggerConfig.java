package com.niu.mall.user.config;

import com.niu.mall.common.config.BaseSwaggerConfig;
import com.niu.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by lihaojie on 2023/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.niu.mall.user.controller")
                .title("mall前台系统")
                .description("mall前台相关接口文档")
                .contactName("lihaojie")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
