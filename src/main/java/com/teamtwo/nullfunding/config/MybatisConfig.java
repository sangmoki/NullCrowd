package com.teamtwo.nullfunding.config;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "com.teamtwo.nullfunding", annotationClass = Mapper.class)
//@MapperScan(value = "com.teamwo.nullfunding", annotationClass = Mapper.class)
@Mapper
public class MybatisConfig {
}
