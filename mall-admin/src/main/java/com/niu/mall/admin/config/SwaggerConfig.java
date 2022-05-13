package com.niu.mall.admin.config;

import com.niu.mall.common.config.BaseSwaggerConfig;
import com.niu.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.niu.mall.admin.controller")
                .title("niu-mall后台系统")
                .description("niu-mall后台相关接口文档")
                .contactName("niu-mall")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
