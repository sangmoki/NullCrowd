package com.teamtwo.nullfunding.config;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;
@Configuration
@MapperScan(basePackages = "com.teamtwo.nullfunding", annotationClass = Mapper.class)
public class MybatisConfig {
}
