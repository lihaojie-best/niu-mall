package com.niu.mall.config;

import com.niu.mall.common.config.BaseSwaggerConfig;
import com.niu.mall.common.domain.SwaggerProperties;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
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
                .apiBasePackage("com.niu.mall.controller")
                .title("mall后台系统")
                .description("mall后台相关接口文档")
                .contactName("李浩杰")
                .version("1.0")
                .contactEmail("2014542916@qq.com")
                .enableSecurity(true)
                .build();
    }
}
