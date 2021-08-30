package com.example.common.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()	// security에서 기본으로 생성하는 login페이지 사용 안 함
                .csrf().disable()
                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
//                .antMatchers("/api/v1/members/**").permitAll() // 가입 및 인증 주소는 누구나 접근가능
                .antMatchers("/**").permitAll() // 가입 및 인증 주소는 누구나 접근가능
                .anyRequest().hasRole("USER"); // 그외
        // 요청은 모두 인증된 회원만 접근 가능
    }
}