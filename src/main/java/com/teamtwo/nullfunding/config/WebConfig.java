package com.teamtwo.nullfunding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/");

        // 이미지 불러올수 있도록 세팅
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/");

        //web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
        //현재 localhost:8888/notice/addImage/1234.jpg
        //로 접속하면 C:/notice/addImage/1234.jpg 파일을 불러온다.
        registry.addResourceHandler("/notice/addImage/**")
                .addResourceLocations("file:///C:/notice/addImage/");
    }


}
