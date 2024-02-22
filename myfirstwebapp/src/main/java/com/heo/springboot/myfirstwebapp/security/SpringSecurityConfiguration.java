package com.heo.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {
	// LDAP or Database 방식 있는데
	// InMemory 사용

	@Bean
	public InMemoryUserDetailsManager createUserDetails() {
		// InMemoryUserDetailsManager의 매개변수로 UserDetails를 전달하여
		// 로그인 시 유저에 대한 정보를 설정
		
		UserDetails userDetails1 = createNewUser("Kim","dummy");
		UserDetails userDetails2 = createNewUser("Heo","this");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
		//InMemoryUserDetailsManager은 가변 매개변수라서 계속 넣을 수 있따.
		}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
			= input -> passwordEncoder().encode(input);
	
		UserDetails userDetails = User.builder()
			.passwordEncoder(passwordEncoder)
			.username(username)
			.password(password)
			.roles("USER","ADMIN")
			.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
