package com.example.common.member.service;

import com.example.common.member.domain.Member;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtService {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    /**
     * 로그인 성공시 사용자 정보를 기반으로 JWT 토큰 생성후 반환
     * @param  member
     * @return
     */
    public String create(final Member member) {
        log.trace("time: {}", expireMin);
        final JwtBuilder builder = Jwts.builder();

        // 헤더 설정, JWT Token = Header + Payload + Signature
        builder.setHeaderParam("typ", "JWT");

        // Payload 설정 - claim 정보 포함
        builder.setSubject("로그인 토큰")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
                .claim("Member", member);

        // secret key를 사용하여 암호화
        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

        // 직렬화
        final String jwt = builder.compact();
        log.debug("토큰 발행: {}", jwt);
        return jwt;
    }

    /**
     * 전달받은 토큰 검증 문제가 있으면 예외 발생
     * @param jwt
     */
    public void checkValid(final String jwt) {
        log.trace("토큰 검증: {}", jwt);
        try {
            Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new IllegalStateException("토큰 검증을 통과하지 못했습니다.");
        }
    }

    /**
     * jwt 토큰 분석하여 필요한 정보 반환
     * @param jwt
     * @return
     */
    public Map<String, Object> get(final String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException("토큰으로부터 정보를 얻을 수 없습니다.");
        }

        log.trace("claims: {}", claims);
        return claims.getBody();
    }

}
