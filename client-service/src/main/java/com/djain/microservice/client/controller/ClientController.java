package com.djain.microservice.client.controller;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djain.microservice.client.model.Emp;
import com.djain.microservice.client.service.ClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	Log log = LogFactory.getLog("ClientController");
	
	@Autowired
	ClientService clientServ;
	
	@PostMapping("/webclient-create")
	@CircuitBreaker(name = "circuitBreaker-client",fallbackMethod = "webclientFallMethod")
	public CompletableFuture<Mono<Emp>> createEmpWeb(@RequestBody Emp request) {
		log.info("inside empclient::::");
		//return webClient.post().uri("/rest/details").header("Authorization", auth).bodyValue(request).retrieve().bodyToMono(Emp.class);
		return clientServ.createEmpServ(request);
	}
	
	@PostMapping("/resttemplate-create")
	@CircuitBreaker(name = "circuitBreaker-client", fallbackMethod = "restclientFallMethod")
	public CompletableFuture<Emp> createEmpRest(@RequestBody Emp request) {
		return clientServ.createEmpRestTemplate(request);
	}
	
//	Feign client not supported since sping 2.4.x	
//	@PostMapping("/feign-create")
//	public Emp createEmpFeign(@RequestBody Emp request) {
//		Emp empdetails = clientProxy.createEmp(request);
//		return new Emp(empdetails.getFirstName(),empdetails.getLastName(),empdetails.getEmailId());
//	}	
	
	public CompletableFuture<Mono<Emp>> webclientFallMethod(@RequestBody Emp request, RuntimeException runtimeException) throws Exception {
		Emp e = new Emp();
		e.setFirstName("Darshan_defaultWeb");
		e.setLastName("Jain_defaultWeb");
		e.setEmailId("darshan@default.web");
		
		return clientServ.createEmpServ(e);
	}
	
	public CompletableFuture<Emp> restclientFallMethod(@RequestBody Emp request, RuntimeException runtimeException) throws Exception {
		Emp e = new Emp();
		e.setFirstName("Darshan_defaultRest");
		e.setLastName("Jain_defaultRest");
		e.setEmailId("darshan@default.rest");
		
		return clientServ.createEmpRestTemplate(e);
	}
	
	
}
