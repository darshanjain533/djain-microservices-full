package com.djain.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	//configure oauth2
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
		serverHttpSecurity.csrf().disable().authorizeExchange(
				exchange -> exchange.pathMatchers("/eureka/**", "/detail").permitAll().anyExchange().authenticated())
				.oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
		return serverHttpSecurity.build();
	}
}

/*
* 
* 
*	To disable this security:
*	1. comment the above code
*	2. comment the depedency in apigateway pom.xml
*		a. spring-boot-starter-oauth2-resource-server
*		b. spring-boot-starter-security
*
*
*/


