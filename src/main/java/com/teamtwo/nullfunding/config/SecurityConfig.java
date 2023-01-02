package com.teamtwo.nullfunding.config;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfig {

    private MemberService memberService;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("//**").authenticated()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//    }

}
