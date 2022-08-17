package com.djain.microservice.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestTemplateAndWebClientConfig {

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder(){
		WebClient.Builder wcbuilder = WebClient.builder().baseUrl("http://employee-service/").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return wcbuilder;
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate RestTemplate() {
	    return new RestTemplate();
	}
	
}
