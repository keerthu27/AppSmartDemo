package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.demo.service.CustomerService;
import com.demo.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	public final String PRODUCTS_URL = "http://localhost:8080/api/v1/prodcuts";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	ResultActions updateCustomer(String prodId) throws Exception {

		return mockMvc.perform(put(PRODUCTS_URL + "/" + prodId)).andExpect(status().is(404));

	}

	@Test
	public void testUpdateCustomer() throws Exception {
		updateCustomer("100000088787");
	}
}
