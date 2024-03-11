package com.example.learnspringsecurity.jwt;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder.PublicKeyJwtDecoderBuilder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JwtSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth.anyRequest().authenticated();
		});
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();

		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return http.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		var user = User.withUsername("kim")
//						.password("{noop}dummy")
				.password("dummy").passwordEncoder(str -> bcryptPasswordEncoder().encode(str)).roles("USER").build();
		var admin = User.withUsername("admin")
//						.password("{noop}dummy")
				.password("dummy").passwordEncoder(str -> bcryptPasswordEncoder().encode(str)).roles("ADMIN").build();

		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;

	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION) // 테이스 베이스 생성 시 해당 스크립트를 추가(실행)
				.build();
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public KeyPair keyPair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // RSA 알고리즘을 위해 인스턴스를 받음
			keyPairGenerator.initialize(2048); // 키 사이즈 설정가능, 키 사이즈가 클수록 보안 증가, 2048비트 RSA 암호화를 원함
			return keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		return new RSAKey
				.Builder((RSAPublicKey)keyPair.getPublic()) // 퍼블릭 키를 인자로 전달
				.privateKey(keyPair.getPrivate())     // 비밀 키 
				.keyID(UUID.randomUUID().toString())   // 아이디 설정
				.build();
	}
	
	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rasKey) { // RSAKey를 이용한 JWKSource 생성
		var jwkSet = new JWKSet(rasKey);

		/*
		 * var jwkSource = new JWKSource() {
		 * 
		 * @Override public List get(JWKSelector jwkSelector, SecurityContext context) {
		 * return jwkSelector.select(jwkSet); } }
		 */
		// 람다함수로
		return (jwkSelector, context) -> jwkSelector.select(jwkSet);

	}

	  

	// 디코딩을 위한 RSA public Key 생성 - Nimbus 프레임워크 사용
	@Bean 
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException{ 
		return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build(); 
	}

}
