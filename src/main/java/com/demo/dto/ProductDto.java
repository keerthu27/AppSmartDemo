package com.demo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Long id;
	private Long customerId;
	private String title;
	private String description;
	private BigDecimal price;
	private String createdAt;
	private String modifiedAt;
}
