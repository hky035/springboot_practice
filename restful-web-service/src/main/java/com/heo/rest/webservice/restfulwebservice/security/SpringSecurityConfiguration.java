package com.heo.rest.webservice.restfulwebservice.security;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.*;

@Configuration
public class SpringSecurityConfiguration{

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1. 모든 요청이 인증을 받아야 되도록(인증받지 못한 요청 통과 X)
		http.authorizeHttpRequests(
				auth-> auth.anyRequest().authenticated()
				);
		// 이 상태에선 모든 요청이 거부됨(인증받지 않았으니까)
		
		// 2. 인증되지 않은 요청에 대해선, 인증 페이지로 이동
		http.httpBasic(withDefaults());
		// 이 상태에서 요청 보내면 로그인 팝업창(또는 페이지) 뜸
		
		
		// 3. csrf 설정 해제(POST,PUT 요청이 가능하도록)
		http.csrf().disable();
		// 이제 POST, PUT 요청 가능해짐
		
		
		return http.build();
	}
}
