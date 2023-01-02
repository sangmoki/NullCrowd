package com.teamtwo.nullfunding.config;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

//    private MemberService memberService;
//
//    @Autowired
//    public SecurityConfig(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @Bean
    public WebSecurityCustomizer configure(){

        return (web) -> web.ignoring().antMatchers("/css/**, /img/**, /js/**, /video/** ");
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()

                .and().build();
    }

}
