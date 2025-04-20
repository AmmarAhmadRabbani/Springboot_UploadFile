package com.dailypractice.service;

import java.util.List;

import com.dailypractice.dto.BankDto;
import com.dailypractice.dto.CustomerDto;

public interface BankService {
	public BankDto add(BankDto bankDto);

	public List<BankDto> getBanks();

	public BankDto getBank(long bankId);

	public List<CustomerDto> getBankCustomer(long customerId);

	public BankDto updateBank(BankDto bankDto);

	public BankDto deleteBank(long bankId);

}
