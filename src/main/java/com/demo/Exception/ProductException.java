package com.demo.Exception;

public class ProductException extends RuntimeException {
	public ProductException(Long productId) {
		super("No product Found for the ID :" + productId);
	}
}
