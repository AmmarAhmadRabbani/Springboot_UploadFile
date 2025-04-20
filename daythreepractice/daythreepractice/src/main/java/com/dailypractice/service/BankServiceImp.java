package com.dailypractice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailypractice.dto.BankDto;
import com.dailypractice.dto.CustomerDto;
import com.dailypractice.entity.Bank;
import com.dailypractice.entity.Customer;
import com.dailypractice.exception.UserNotFoundException;
import com.dailypractice.repository.BankRepository;

@Service
public class BankServiceImp implements BankService {
	@Autowired
	private BankRepository repository;

//	@Autowired
//	private CustomerRepository customerRepository;

	@Override
	public BankDto add(BankDto bankDto) {
		if (bankDto != null) {
			Bank bank = new Bank();
			BeanUtils.copyProperties(bankDto, bank);
			Bank saveBank = repository.save(bank);
			BankDto dto = new BankDto();
			BeanUtils.copyProperties(saveBank, dto);
			return dto;
		}
		throw new UserNotFoundException("bank detail not available");

	}

	@Override
	public List<BankDto> getBanks() {
		List<BankDto> bankList = new ArrayList<>();
		List<Bank> findAll = repository.findAll();
		if (findAll.isEmpty()) {
			throw new UserNotFoundException("User not present");
		}
		findAll.forEach(i -> {
			BankDto bankDto = new BankDto();
			BeanUtils.copyProperties(i, bankDto);
			bankList.add(bankDto);
		});

		return bankList;
	}

	@Override
	public BankDto getBank(long bankId) {
		Bank bank = repository.findById(bankId).orElseThrow(() -> new UserNotFoundException("id not found"));
		BankDto bankDto = new BankDto();
		BeanUtils.copyProperties(bank, bankDto);
		return bankDto;
	}

	@Override
	public BankDto updateBank(BankDto bankDto) {
		Bank bank = repository.findById(bankDto.getBankId()).orElseThrow(() -> new UserNotFoundException("invalid id"));
		BeanUtils.copyProperties(bankDto, bank);
		Bank save = repository.save(bank);
		BeanUtils.copyProperties(save, bankDto);
		return bankDto;
	}

	@Override
	public BankDto deleteBank(long bankId) {
		Bank bank = repository.findById(bankId).orElseThrow(() -> new UserNotFoundException("invalid id"));
		repository.deleteById(bankId);
		return new BankDto();
	}

	@Override
	public List<CustomerDto> getBankCustomer(long bankId) {
		Bank bank = repository.findById(bankId).orElseThrow(() -> new UserNotFoundException("invalid bank id"));
		List<Customer> customersList = bank.getCustomers();
		List<CustomerDto> customerDtoList = new ArrayList<>();
		customersList.forEach(i -> {
			CustomerDto customerDto = new CustomerDto();
			BeanUtils.copyProperties(i, customerDto);
			customerDtoList.add(customerDto);
		});

		return customerDtoList;

	}
	
}