package com.child.programming.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description：跨域请求配置
 * @Author：yangfan
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //TODO 开发环境  添加映射路径，目前是所有
        registry.addMapping("/**")
                //请求类型
                .allowedMethods("GET", "POST")
                .allowCredentials(true) //cookie跨域
                //允许跨域网址
                .allowedOrigins("http://localhost:8000");
    }
}
