package com.dailypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailypractice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
