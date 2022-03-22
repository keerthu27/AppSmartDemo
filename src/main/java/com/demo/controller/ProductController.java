package com.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ProductDto;
import com.demo.service.ProductService;

/***
 * Products Controller class
 * @author Keerthana
 *
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * Delete product 	  
	 * @param productId
	 */
	@DeleteMapping(value = "/products/{productId}")
	public void deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
	}

	/**
	 * get the product details by particular product Id
	 * @param productId Product Id
	 * @return product 
	 */
	@GetMapping(value = "/products/{productId}")
	public ProductDto getProductById(@PathVariable Long productId) {
		return productService.getProductbyId(productId);
	}

	/**
	 * Edit the product with product Id. Can be edited one or more data.
	 * @param productId Product Id
	 * @param title title of the product 
	 * @param description description of the product
	 * @param price price of the product
	 * @return updated product
	 */
	@PutMapping(value = "/products/{productId}")
	public ProductDto updateProduct(@PathVariable Long productId, @RequestParam(required = false) String title,
			@RequestParam(required = false) String description, @RequestParam(required = false) BigDecimal price) {
		return productService.updateProduct(productId, title, price, description);
	}

}
