package com.teamtwo.nullfunding.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.teamtwo.nullfunding")
public class EmailAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailAuthApplication.class, args);
    }

}
