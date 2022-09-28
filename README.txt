# djain-microservices-full
Full Microservice architecture


# spring-boot-microservices-new
This repository contains the latest source code of the spring-boot-microservices tutorial

https://www.youtube.com/playlist?list=PLSVW22jAG8pBnhAdq9S8BpLnZ0_jVBj0c
https://github.com/SaiUpadhyayula/spring-boot-microservices-new.git

https://www.youtube.com/watch?v=wUVlvX-UeSQ&t=4533s
https://github.com/tecnotab/tecnotab-microservices-youtube.git


		Sno	|	Spring Application Name				|		Port(s)				|		Possible Annotations		|		URLs							|		Comments
		-----------------------------------------------------------------------------------------------------------------------------------------------------------------
		1.	|	spring-cloud-config-service			|		8888				|		@EnableConfigServer			|		localhost:8888					|	Do have eureka client service registration. http://localhost:8888/client-service/dev
		2.	|	spring-cloud-eureka-service			|		8761				|		@EnableEurekaServer			|		localhost:8761/eureka/web		|	eureka:password
		3.	|	employee-service (/emprest)			|		5000,5001			|		@EnableEurekaClient			|		localhost:5000					|	
		4. 	|	client-service (/client)			|		7000				|									|		localhost:7000					|
		5. 	|	detail-service (/detail)			|		0					|									|		localhost:3000					|	This is a test service.if server.port = 0 is given --> Springboot will allocate available port of choice.
		6. 	|	spring-cloud-gateway-service		|		80					|	2,	@EnableWebFluxSecurity		|		localhost:80 or localhost		|	This section is secured with oauth2 using keycloak. 
		7. 	|	key cloak token server				|		8080				|									|		localhost:8080					|	admin:admin is the username and password
		8.	|	zipkin server						|		9411				|									|		localhost:9411					|	
		9.	|	spring-cloud-notification-service	|		6000, 9092			|									|										|	Start the Apache Kafka server / zookeeper server
		
if the security mechanism is enable on gateway-service, then the spring security also has to be applied to all the service where the clients needs to be registered. Like, spring security has to be enabled in eureka, employee, client, detail - on then those would be registered in eureka server. 		
		
		
Start:
1. spring-cloud-config-service	
2. Key cloak token server
3. spring-cloud-eureka-service
4. spring-cloud-gateway-service
5. employee on 5000 and 0
6. client
7. detail
8. zipkin

============ Zipkin ============

download the jar zipkin-server-2.23.18-exec.jar
run the jar as 
cd /Volumes/SandiskMac/eclipse-workspace-mac-2022-06/djain-microservice/spring-install-process
java -jar zipkin-server-2.23.18-exec.jar

RABBIT_URI=amqp://localhost java -jar /Volumes/SandiskMac/eclipse-workspace-mac-2022-06/djain-microservice/spring-install-process/zipkin-server-2.23.18-exec.jar

localhost:9411


========= RabbitMQ ===============

Rabbit MQ
https://www.rabbitmq.com/install-homebrew.html

Start 	--> brew services start rabbitmq
Stop 	--> brew services stop rabbitmq
Restart --> brew service restart rabbitmq

localhost:15672

guest
guest

================= Apache Kafka =============
to install kafka --> brew install kafka
vi /usr/local/etc/kafka/server.properties
Changes listeners=http://localhost:9092

Zookeeper <to find out>
/usr/local/opt/kafka/bin/zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
/usr/local/opt/kafka/bin/zookeeper-server-stop


Kafka server localhost:9092
/usr/local/opt/kafka/bin/kafka-server-start /usr/local/etc/kafka/server.properties
/usr/local/opt/kafka/bin/kafka-server-stop


================== Key Cloak ===============
Before you start
	Make sure you have OpenJDK 11 or newer installed.

Download Keycloak
	First step is to download and extract keycloak-18.0.0.zip from the Keycloak website.

After extracting you should have a directory named keycloak-18.0.0.

Start Keycloak
	From a terminal open the directory keycloak-18.0.0, then to start Keycloak run the following command.
	
	On Linux run:		bin/kc.sh start-dev
	On Windows run:		bin/kc.bat start-dev
	
	
	For docker:
	docker run -p 8100:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev

Create an admin user
	Keycloak does not come with a default admin user, which means before you can start using Keycloak you need to create an admin user.
	To do this open http://localhost:8080/, then fill in the form with your preferred username and password. admin:admin

Login to the admin console
	Go to the Keycloak Admin Console and login with the username and password you created earlier.


Pending 
1. Send email. spring-boot-email-service -to be implemented fully.
2. JUnit 5 and Mokito
3. ELK --> https://www.javainuse.com/spring/springboot-microservice-elk
4. Docker and 
5. Apache Kafka using spring cloud stream
