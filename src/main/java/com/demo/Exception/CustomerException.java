package com.demo.Exception;

public class CustomerException extends RuntimeException {

	public CustomerException(Long customerId) {
		super("Customer is not found" + customerId);
	}
}
