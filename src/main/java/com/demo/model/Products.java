package com.demo.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "title")
	@NotEmpty(message = "Title should not be Empty")
	@NotBlank(message = "Title should not be Blank")
	@NonNull
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "price", precision = 10, scale = 2)
	@NotNull(message = "Please provide the price")
	private BigDecimal price;

	@Column(name = "is_deleted")
	@Builder.Default
	private Boolean isDeleted = false;

	@Column(name = "created_at")
	private ZonedDateTime createdAt;

	@Column(name = "modified_at")
	private ZonedDateTime modifiedAt;

}
