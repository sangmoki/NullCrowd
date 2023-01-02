package com.teamtwo.nullfunding.config;

import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
import org.springframework.context.annotation.Configuration;

@Configuration
=======
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.teamtwo.nullfunding", annotationClass = Mapper.class)
//@MapperScan(value = "com.teamwo.nullfunding", annotationClass = Mapper.class)
>>>>>>> origin/master
@Mapper
public class MybatisConfig {
}