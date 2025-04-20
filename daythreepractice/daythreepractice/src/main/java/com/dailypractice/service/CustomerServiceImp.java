package com.dailypractice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailypractice.dto.CustomerDto;
import com.dailypractice.entity.Bank;
import com.dailypractice.entity.Customer;
import com.dailypractice.exception.UserNotFoundException;
import com.dailypractice.repository.BankRepository;
import com.dailypractice.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private BankRepository bankRepository;

	@Override
	public CustomerDto add(CustomerDto customerDto) {
		Bank bank = bankRepository.findById(customerDto.getBankId())
				.orElseThrow(() -> new UserNotFoundException("invalid bank id"));
		Customer customer = new Customer();
		customer.setBank(bank);
		BeanUtils.copyProperties(customerDto, customer);
		Customer save = repository.save(customer);
		CustomerDto dto = new CustomerDto();
		BeanUtils.copyProperties(save, dto);
		return dto;

	}

	@Override
	public List<CustomerDto> getCustomer() {
		List<CustomerDto> customeList = new ArrayList<>();
		List<Customer> findAll = repository.findAll();
		if (findAll.isEmpty()) {
			throw new UserNotFoundException("getting user detials failed");
		}
		findAll.forEach(i -> {
			CustomerDto customerDto = new CustomerDto();
			BeanUtils.copyProperties(i, customerDto);
			Bank bank = i.getBank();
			BeanUtils.copyProperties(bank, customerDto);
			customeList.add(customerDto);

		});
		return customeList;
	}

	@Override
	public CustomerDto getById(long customerId) {
		Customer customer = repository.findById(customerId).orElseThrow(() -> new UserNotFoundException("invalid id"));
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(customer, customerDto);
		Bank bank = customer.getBank();
		BeanUtils.copyProperties(bank, customerDto);
		return customerDto;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Customer customer = repository.findById(customerDto.getCustomerId())
				.orElseThrow(() -> new UserNotFoundException("user not present"));
		BeanUtils.copyProperties(customerDto, customer);

		Customer save = repository.save(customer);
		BeanUtils.copyProperties(save, customerDto);
		return customerDto;
	}

	@Override
	public CustomerDto deleteCustomer(long customerId) {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new UserNotFoundException("id not present"));
		repository.deleteById(customerId);
		return new CustomerDto();
	}

}
