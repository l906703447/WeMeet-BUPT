package com.wemeet.wemeet;

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
 * swagger2 配置类
 * @author xieziwei99
 * 2019-07-21
 */
@Configuration      // 让spring来加载该配置类
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wemeet.wemeet.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {     // 创建API的基本信息
        return new ApiInfoBuilder()
                .title("GoldBug APIs")
                .description("仿照教程写的")
                .contact("xieziwei")
                .version("1.0")
                .build();
    }
}
