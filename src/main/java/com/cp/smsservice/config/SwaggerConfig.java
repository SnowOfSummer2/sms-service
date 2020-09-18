package com.cp.smsservice.config;

import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cp.smsservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("短信服务 RestFul Api 文档")
                .contact(new Contact("彭俊龙","暂不开放","237523747@qq.com"))
                .version("1.0")
                .build();
    }
}
