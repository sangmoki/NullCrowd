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
//                .loginProcessingUrl("/member/login")
//                .defaultSuccessUrl("/")
                .successForwardUrl("/")
                .and().build();
    }


    /* 비밀번호 암호화에 사용할 객체 : BCryptPasswordEncoder bean 등록 */




    /* HTTP 요청에 대한 권한 설정 */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        /* csrf : 토큰 위주의 공격을 막기 위한 작업 (default가 'on'상태)*/
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .anyRequest().permitAll()    // 등록되지 않은 경우로는 누구나 접근 가능
//                .and()
//                .formLogin()   // 로그인 form을 따로 이용해 로그인 처리할 것이다.
//                .loginPage("content/member/login")   // login page로 해당 로그인페이지에서 submit요청하는 경로로 지정하겠다는 의미
//                .successForwardUrl("/")       // 성공 시 페이지 설정
//                .and()
//                .logout()                     // 로그아웃 설정
//                .logoutRequestMatcher(new AntPathRequestMatcher("content/member/logout"))  // 로그아웃 시 요청 경로
//                .deleteCookies("JSESSIONID")   // 쿠키 제거
//                .invalidateHttpSession(true)                        // session정보 무효화
//                .and()
//                .exceptionHandling()
//                // 인가/인증 exception 핸들리설정
//                .accessDeniedPage("/common/denied")  // 인가되지 않았을 때 - 권한이 없는 기능을 요청했을 때
//                // 호출될 페이지
//                .and().build();
//    }

    /* 사용자 인증을 위해서 사용할 service bean 등록 , 사용할 비밀번호 인코딩 방식 설정 */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        System.out.println("test" + "test");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberService)   // 사용자정보 가져오고
                .passwordEncoder(passwordEncoder())
                .and().build();
    }
}

