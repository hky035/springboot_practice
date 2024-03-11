package com.example.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {

	private JwtEncoder jwtEncoder;

	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	@PostMapping("/authenticate")
	public JwtResponse authenticate(Authentication authentication) { // org.springframework.security.core.Authentication

		return new JwtResponse(createToken(authentication));
	}

	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder() // JwtClaimSet ; JWT이 전달한 클레임을 나타내는 JSON 객체
				.issuer("self") // 발행 자 : self(같은 애플리케이션 이니까)
				.issuedAt(Instant.now()) // iat 설장
				.expiresAt(Instant.now().plusSeconds(60 * 30)) // 30분의 만료기한
				.subject(authentication.getName()) // 주제
				.claim("scope", createScope(authentication)) // 클레임에 scope(유저가 가진 권한) 넣어줌 / 앞선 테스트에서 authentication을
																// 응답했을 때 나오는 principal - authorities 확인
				.build();

		/*
		 * JwtEncoderParameters parameters = JwtEncoderParameters.from(claims); // 인코더의
		 * 파라미터를 생성, 인자로 클레임 셋을 넘겨줌 return
		 * jwtEncoder.encode(parameters).getTokenValue(); //
		 */
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); //
	}

	private Object createScope(Authentication authentication) {
		// authentication의 pricipal - authoriites의 authority(역할)을 담을라고 한다.
		return authentication.getAuthorities().stream()
											 .map(a -> a.getAuthority())
											 .collect(Collectors.joining(" ")); // autority가 모두 수집되어 결합, " " 공백으로 구분
	}
}

record JwtResponse(String token) {
} // 토큰과 함께 응답할 객체