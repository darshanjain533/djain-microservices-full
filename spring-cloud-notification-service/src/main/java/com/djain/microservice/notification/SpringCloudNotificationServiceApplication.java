package com.djain.microservice.notification;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudNotificationServiceApplication.class, args);
	}

	@Bean
	Consumer<Message<OrderDto>> notificationEventSupplier() {
		return message -> {
			try {
				new EmailSender().sendEmail(message.getPayload());
			} catch (InterruptedException e) {
				throw new RuntimeException("Something went wrong while sending email");
			}
		};
	}
}
