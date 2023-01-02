package com.teamtwo.nullfunding.config;

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
