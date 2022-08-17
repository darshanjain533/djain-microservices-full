package com.djain.microservice.client.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.djain.microservice.client.config.RestTemplateAndWebClientConfig;
import com.djain.microservice.client.model.Emp;

import reactor.core.publisher.Mono;

@Service
public class ClientService {
	
	@Autowired
	RestTemplateAndWebClientConfig wc;
	
	public CompletableFuture<Mono<Emp>> createEmpServ(Emp empl) {
		Mono<Emp> b = wc.webClientBuilder().build().post().uri("/emprest/create").bodyValue(empl).retrieve()
				.bodyToMono(Emp.class);
		
		return CompletableFuture.supplyAsync(()->b);
		
		//return CompletableFuture.completedFuture(b);
		
		// return
		// webClientBuilder.post().uri("/emprest/create").bodyValue(empl).retrieve().bodyToMono(Emp.class);
		// without using LB.
	}
	
	public CompletableFuture<Emp> createEmpRestTemplate(Emp request) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		headers.setAll(map);
		HttpEntity<?> httprequest = new HttpEntity<>(request, headers);
		ResponseEntity<Emp> responseEntity = wc.RestTemplate().postForEntity("http://employee-service/emprest/create", httprequest, Emp.class); //http://localhost:5000/emprest/create - localhost and port is replaced with spring.application.name when eureka is enabled.
		Emp empdetails = responseEntity.getBody();
		
		//return CompletableFuture.completedFuture(new Emp(empdetails.getFirstName(),empdetails.getLastName(),empdetails.getEmailId()));
		
		return CompletableFuture.supplyAsync(
				() -> new Emp(empdetails.getFirstName(),empdetails.getLastName(),empdetails.getEmailId()
						));
	}
}