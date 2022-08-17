package com.djain.microservice.notification;


public class OrderDto {
	
	private String orderNumber;
	
    public OrderDto() {
		super();
	}

	public OrderDto(String orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "OrderDto [orderNumber=" + orderNumber + "]";
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
