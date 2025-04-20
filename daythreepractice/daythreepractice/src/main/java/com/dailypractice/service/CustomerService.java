package com.dailypractice.service;

import java.util.List;

import com.dailypractice.dto.CustomerDto;

public interface CustomerService {
	public CustomerDto add(CustomerDto customerDto);

	public List<CustomerDto> getCustomer();

	public CustomerDto getById(long customerId);

	public CustomerDto updateCustomer(CustomerDto customerDto);
	
	public CustomerDto deleteCustomer(long customerId);

}
