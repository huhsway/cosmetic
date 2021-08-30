package com.example.common.config;

import com.example.common.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.trace("요청 메소드: " + request.getMethod());
        log.trace("요청 Path: " + request.getServletPath());

        // option 요청은 바로 통과
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())){
            return true;
        }

        // request의 parameter에서 auth_token으로 넘어온 경우 찾음
        String token = request.getHeader("jwt-auth-token");

        if (token != null && token.length() > 0){
            // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생시킨다
            jwtService.checkValid(token);
            log.trace("토큰 사용 가능: {}", token);
            return true;
        } else{
            throw new RuntimeException("인증 토큰이 없습니다.");
        }

    }
}
