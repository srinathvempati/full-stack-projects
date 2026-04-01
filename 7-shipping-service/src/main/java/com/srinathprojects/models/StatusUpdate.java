package com.srinathprojects.models;

public enum StatusUpdate {
	
	ORDER_PLACED("PLACED"),
	ORDER_CANCELLED("CANCELLED"),
	ORDER_SHIPPED("SHIPPED"),
	ORDER_DELIVERED("DELIVERED"),
	ORDER_RETURNED("RETURNED");
	
	private final String status;

	public String getStatus() {
		return status;
	}

	private StatusUpdate(String status) {
		this.status = status;
	}
	
	
	
	

}
