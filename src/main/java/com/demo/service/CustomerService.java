package com.demo.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.Exception.CustomerException;
import com.demo.dto.CustomerDto;
import com.demo.model.Customers;
import com.demo.repository.CustomerRepository;

/**
 * Customer service class
 * @author Keerthana
 *
 */
@Service
public class CustomerService {

	@Autowired
	CustomerRepository custRepository;

	/**
	 * Get all the existing customers
	 * @param pageable
	 * @return Paginated List of the existing customer
	 */
	public List<CustomerDto> getAllCustomer(Pageable pageable) {
		return custRepository.findAll(pageable).getContent().stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	/**
	 * createCustomer methos handles create customers request.
	 * Title shouldnot be null, empty or blank
	 * @param title
	 * @return Customer
	 */
	public CustomerDto createCustomer(String title) {

		Customers customer = Customers.builder().title(title).createdAt(ZonedDateTime.now()).build();

		return convertEntityToDto(custRepository.save(customer));
	}

	/**
	 * Deleting the customer
	 * @param customerId Customer Id
	 */
	public void deleteCusomer(Long customerId) {
		custRepository.findById(customerId).orElseThrow(() -> new CustomerException(customerId));

		custRepository.deleteById(customerId);
	}

	/**
	 * Get the customer by Id
	 * @param customerId Customer Id
	 * @return Customer details
	 */
	public CustomerDto getCustomerById(Long customerId) {
		return convertEntityToDto(
				custRepository.findById(customerId).orElseThrow(() -> new CustomerException(customerId)));
	}

	/**
	 *  Update the Customer using customet Id. Title should not be null or empty
	 * @param custId
	 * @param title
	 * @return
	 */
	public CustomerDto updateCustomer(Long custId, String title) {

		Customers custExistingDetails = custRepository.findById(custId)
				.orElseThrow(() -> new CustomerException(custId));

		custExistingDetails.setTitle(title);
		custExistingDetails.setModifiedAt(ZonedDateTime.now());

		return convertEntityToDto(custRepository.save(custExistingDetails));

	}

	/**
	 * Converting customer model to customer DTO
	 * @param customer
	 * @return
	 */
	private CustomerDto convertEntityToDto(Customers customer) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		CustomerDto customerDto = CustomerDto.builder().id(customer.getId()).title(customer.getTitle())
				.createdAt(customer.getCreatedAt().format(formatter))
				.modifiedAt(customer.getModifiedAt() != null ? customer.getModifiedAt().format(formatter) : "").build();

		return customerDto;
	}
}
