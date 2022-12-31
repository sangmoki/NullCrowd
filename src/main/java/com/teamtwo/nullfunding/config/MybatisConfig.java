package com.teamtwo.nullfunding.config;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@MapperScan(value = "com.teamwo.nullfunding", annotationClass = Mapper.class)
@Mapper
public class MybatisConfig {
}
