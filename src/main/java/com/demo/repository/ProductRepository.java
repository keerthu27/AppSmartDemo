package com.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	Page<Products> findByCustomerId(Long customerId, Pageable pageable);
}
