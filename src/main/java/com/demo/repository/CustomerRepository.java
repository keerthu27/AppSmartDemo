package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
