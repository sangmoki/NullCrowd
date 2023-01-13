package com.teamtwo.nullfunding.config;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    private MemberService memberService;

    @Autowired
    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    /* 시큐리티 설정을 무시할 정적 리소스를 등록 (resources 안의 static 폴더 내부의 정적리소스 유형 무시) */
    @Bean
    public WebSecurityCustomizer configure(){

        return (web) -> web.ignoring().antMatchers("/css/**, /img/**, /js/**, /video/** ");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
         .and()
                .formLogin()
                .loginPage("/member/login")
                .successForwardUrl("/")
         .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
          .and()
                .build();
    }

    /* 사용자 인증을 위해서 사용할 service bean 등록 , 사용할 비밀번호 인코딩 방식 설정 */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        System.out.println("authManager");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberService)   // 사용자정보 가져오고
                .passwordEncoder(passwordEncoder())
                .and().build();
    }
}

