package com.djain.microservice.notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
	
	Log log = LogFactory.getLog(getClass());
	
    public void sendEmail(OrderDto orderDto) throws InterruptedException {
        log.info("Sending Email Confirmation for Order - "+ orderDto.getOrderNumber());
        Thread.sleep(100);
        log.info("Email Sent!!");
    }
}
