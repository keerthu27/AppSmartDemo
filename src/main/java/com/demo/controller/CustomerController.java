package com.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.demo.dto.CustomerDto;
import com.demo.dto.ProductDto;
import com.demo.service.CustomerService;
import com.demo.service.ProductService;

/**
 * Customer Controller class.
 * @author keerthana
 *
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	/**
	 * Get all the existing customers
	 * @param page zero-based page index, must not be negative 
	 * @param size  the size of the page to be returned, must be greater than 0.
	 * @return Paginated List of the existing customer
	 */
	@GetMapping(value = "/customers")
	public List<CustomerDto> getAllCustomer(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return customerService.getAllCustomer(PageRequest.of(page, size));
	}

	/**
	 * createCustomer methos handles create customers request.
	 * Title shouldnot be null, empty or blank
	 * @param title
	 * @return Customer
	 */
	@PostMapping(value = "/customers")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDto createCustomer(@RequestParam String title) {
		return customerService.createCustomer(title);
	}

	/**
	 * Deleting the customer
	 * @param customerId Customer Id
	 */
	@DeleteMapping(value = "/customers/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCusomer(customerId);
	}

	/**
	 * Get the customer by Id
	 * @param customerId Customer Id
	 * @return Customer details
	 */
	@GetMapping(value = "/customers/{customerId}")
	public CustomerDto getCustomerById(@PathVariable Long customerId) {
		return customerService.getCustomerById(customerId);
	}

	/**
	 * Update the Customer using customet Id. Title should not be null or empty
	 * @param customerId
	 * @param title
	 * @return
	 */
	@PutMapping(value = "/customers/{customerId}")
	public CustomerDto updateCustomer(@PathVariable Long customerId, @RequestParam String title) {
		return customerService.updateCustomer(customerId, title);
	}

	/***
	 * Get All the paginated list of particular customer products
	 * @param customerId Customer Id
	 * @param page  zero-based page index, must not be negative.
	 * @param size  the size of the page to be returned, must be greater than 0.
	 * @return Paginated list of customer products
	 */
	@GetMapping(value = "/customers/{customerId}/products")
	public List<ProductDto> getAllProduct(@PathVariable Long customerId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return productService.getAllProductbyCustId(customerId, PageRequest.of(page, size));
	}

	/**
	 * createProduct methos handle creating product based on customer id. 
	 * title and price should not be null or empty
	 * @param customerId - Customer Id
	 * @param title - title of the product
	 * @param description - description of the product
	 * @param price - price of the product
	 * @return Product
	 */
	@PostMapping(value = "/customers/{customerId}/products")
	public ProductDto createProduct(@PathVariable Long customerId, @RequestParam String title,
			@RequestParam(required = false) String description, @RequestParam BigDecimal price) {
		return productService.creteProduct(customerId, title, description, price);
	}
}
