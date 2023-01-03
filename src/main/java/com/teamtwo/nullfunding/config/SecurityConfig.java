package com.teamtwo.nullfunding.config;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MemberService memberService;

    @Autowired
    public SecurityConfig(MemberService memberService){

        this.memberService = memberService;
    }

    /* 비밀번호 암호화에 사용할 객체 : BCryptPasswordEncoder bean 등록 */
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    /* 시큐리티 설정을 무시할 정적 리소스를 등록 (resources 안의 static 폴더 내부의 정적리소스 유형 무시) */
    @Bean
    public WebSecurityCustomizer configure(){

        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    /* HTTP 요청에 대한 권한 설정 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /* csrf : 토큰 위주의 공격을 막기 위한 작업 (default가 'on'상태)*/
        return http.csrf().disable()
                .authorizeHttpRequests()   // 요청에 대한 권한 체크를 어떻게할건지
                .antMatchers("/menu/**").authenticated() // /menu/**에 대해서는 하나하나 권한을  등록하겠다.
                .antMatchers(HttpMethod.GET, "/menu/**").hasRole("MEMBER") // hasRole은 ROLE_를 달아주며 ROLE_MEMBER와 같으며 허용하겠다.
                .antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
                .antMatchers("/order/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()    // 등록되지 않은 경우로는 누구나 접근 가능
                .and()
                .formLogin()   // 로그인 form을 따로 이용해 로그인 처리할 것이다.
                .loginPage("/member/login")   // login page로 해당 로그인페이지에서 submit요청하는 경로로 지정하겠다는 의미
                .successForwardUrl("/")       // 성공 시 페이지 설정
                .and()
                .logout()                     // 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))  // 로그아웃 시 요청 경로
                .deleteCookies("JSESSIONID")   // 쿠키 제거
                .invalidateHttpSession(true)                        // session정보 무효화
                .and()
                .exceptionHandling()
                // 인가/인증 exception 핸들리설정
                .accessDeniedPage("/common/denied")  // 인가되지 않았을 때 - 권한이 없는 기능을 요청했을 때
                // 호출될 페이지
                .and().build();
    }

    /* 사용자 인증을 위해서 사용할 service bean 등록 , 사용할 비밀번호 인코딩 방식 설정 */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberService)   // 사용자정보 가져오고
                .passwordEncoder(passwordEncoder())
                .and().build();
    }
}

