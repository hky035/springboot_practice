package com.heo.springboot.myfirstwebapp.security;

import java.util.function.Function;
import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        		http.authorizeHttpRequests(
        				authorize -> authorize
                        .anyRequest().authenticated())
                .formLogin(withDefaults()) // 여기까지가 기본 설정
                .csrf(csrf -> csrf.disable()) // 여기서부터 우리가 추가했어야할 2가지 기능
                .headers(headers -> headers.frameOptions(options -> options.disable()));
 
        return http.build();
    }
}

