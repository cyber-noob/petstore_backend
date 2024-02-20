package com.netrunner.petstore;

import com.netrunner.petstore.service.OpenPolicyAgentAuthorizationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class PetstoreApplication {

	private static final String[] AUTH_WHITELIST = {
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			"/v3/api-docs/**",
			"/api/public/**",
			"/api/public/authenticate",
			"/actuator/*",
			"/swagger-ui/**",
			"/wishlist/**",
			"/search/**",
			"/pdp/**",
			"/search/**"
	};

	@Autowired
	OpenPolicyAgentAuthorizationManager openPolicyAgentAuthorizationManager;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(a -> a
						.requestMatchers(AUTH_WHITELIST).permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.anyRequest()
						.access(openPolicyAgentAuthorizationManager)
				)
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				);

		return http.build();
	}
	
	private static final String[] CORS_WHITELIST = {
			"/wishlist/**",
			"/cart/**"
	};

	private static final String[] CORS_METHODS = {
			"POST",
			"OPTIONS"
	};

	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}

}
