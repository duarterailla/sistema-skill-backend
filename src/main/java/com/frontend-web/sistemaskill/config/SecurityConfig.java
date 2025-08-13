package com.neki.sistemaskill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/auth/**", "/api/categorias/**", "/api/skills/**", "/api/user-skills/**", "/api/usuarios/**").permitAll()
				.anyRequest().authenticated()
			)
			.csrf(csrf -> csrf.disable())
			.cors(Customizer.withDefaults());
		return http.build();
	}
}

