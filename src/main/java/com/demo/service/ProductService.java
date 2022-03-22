package com.demo.service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.Exception.CustomerException;
import com.demo.Exception.ProductException;
import com.demo.dto.ProductDto;
import com.demo.model.Customers;
import com.demo.model.Products;
import com.demo.repository.CustomerRepository;
import com.demo.repository.ProductRepository;

/**
 * Product Service Class
 * @author Keerthana
 *
 */
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	

	/**
	 * Return paginated list of all customer product
	 * @param custId
	 * @param pageable
	 * @return
	 */
	public List<ProductDto> getAllProductbyCustId(Long custId,Pageable pageable){
		return productRepository.findByCustomerId(custId, pageable)
				.getContent()
				.stream()
				.map(this::convertEntitytoDto)
				.collect(Collectors.toList());
	}
	
	/**
	 * Create product based on customer id
	 * @param custId
	 * @param title
	 * @param description
	 * @param price
	 * @return
	 */
	public ProductDto creteProduct(Long custId,String title,String description,BigDecimal price) {
		
		Customers customer = customerRepository
                .findById(custId)
                .filter(p -> !p.getIsDeleted())
                .orElseThrow(() -> new CustomerException(custId));
	
		Products product = Products.builder().customerId(custId)
				.title(title)
				.price(price)
				.description(description)
				.createdAt(ZonedDateTime.now())
				.build();
				
		
		return convertEntitytoDto(productRepository.save(product));
	}
	
	/**
	 * Delete the product based on product id
	 * @param prodId
	 */
	public void deleteProduct(Long prodId) {
		productRepository.findById(prodId).orElseThrow(()-> new ProductException(prodId));
		productRepository.deleteById(prodId);
	}
	
	/**
	 * get the product details based on the product od
	 * @param prodId
	 * @return
	 */
	public ProductDto getProductbyId(Long prodId) {
		return convertEntitytoDto(productRepository.findById(prodId).orElseThrow(()->new ProductException(prodId)));
	}
	
	/**
	 * Update the product based on product id
	 * @param prodId
	 * @param title
	 * @param price
	 * @param descrption
	 * @return
	 */
	public ProductDto updateProduct(Long prodId,String title,BigDecimal price,String descrption) {
		Products prodExistingDetails = productRepository.findById(prodId).orElseThrow(()-> new ProductException(prodId));
		
		if(title !=null) {
			prodExistingDetails.setTitle(title);
		}
		
		if(price != null) {
			prodExistingDetails.setPrice(price);
		}
		
		if(descrption != null) {
			prodExistingDetails.setDescription(descrption);
		}
				
		prodExistingDetails.setModifiedAt(ZonedDateTime.now());
		return convertEntitytoDto(productRepository.save(prodExistingDetails));
	}
	
	
	/**
	 * Converting product entity to product DTO
	 * @param product
	 * @return
	 */
	private ProductDto convertEntitytoDto(Products product) {
		 final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ProductDto productDto = ProductDto.builder()
				.id(product.getId())
				.customerId(product.getCustomerId())
				.title(product.getTitle())
				.price(product.getPrice())
				.description(product.getDescription())
				.createdAt(product.getCreatedAt().format(formatter))
				.modifiedAt(product.getModifiedAt()!=null ? product.getModifiedAt().format(formatter):"")
				.build();
		
		return productDto;
	}
	
}
