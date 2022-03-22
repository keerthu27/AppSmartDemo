package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.service.CustomerService;
import com.demo.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

	public final String CUSTOMERS_URL = "http://localhost:8080/api/v1/customers";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	ResultActions createCustomer(String title) throws Exception {

		return mockMvc.perform(post(CUSTOMERS_URL).contentType(MediaType.APPLICATION_JSON).param("title", title))
				.andExpect(status().is(201)).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
				.andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isNotEmpty());

	}

	ResultActions getAllCustomer() throws Exception {

		return mockMvc.perform(get(CUSTOMERS_URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isNotEmpty());

	}

	ResultActions createNullCustomer(String title) throws Exception {

		return mockMvc.perform(post(CUSTOMERS_URL).contentType(MediaType.APPLICATION_JSON).param("title", title))
				.andExpect(status().is(400));

	}

	ResultActions updateCustomer(String title, String customerId) throws Exception {

		return mockMvc.perform(
				put(CUSTOMERS_URL + "/" + customerId).contentType(MediaType.APPLICATION_JSON).param("title", title))
				.andExpect(status().is(404));

	}

	@Test
	public void testCreateCustomer() throws Exception {
		createCustomer("KKK");
	}

	@Test
	public void testCreateNullCustomer() throws Exception {
		createNullCustomer(null);
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		updateCustomer("CCC", "100000088787");
	}

	public void testGetAllCustomers() throws Exception {
		getAllCustomer();
	}
}
