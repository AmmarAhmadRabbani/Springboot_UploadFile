package com.dailypractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dailypractice.dto.BankDto;
import com.dailypractice.dto.CustomerDto;
import com.dailypractice.response.SuccessResponse;
import com.dailypractice.service.BankService;

@RestController
public class BankController {
	@Autowired
	private BankService service;

	@PostMapping("/addBank")
	public ResponseEntity<SuccessResponse> add(@RequestBody BankDto bankDto) {
		BankDto add = service.add(bankDto);
		if (add != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "added", add), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "adding failed", null), HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getBanks")
	public ResponseEntity<SuccessResponse> getBanks() {
		List<BankDto> banks = service.getBanks();
		if (!banks.isEmpty()) {
			return new ResponseEntity<>(new SuccessResponse(false, "getting successfully", banks), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "getting failed", null), HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getBank/{bankId}")
	public ResponseEntity<SuccessResponse> getBank(@PathVariable long bankId) {
		BankDto bank = service.getBank(bankId);
		if (bank != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "getting successfully", bank), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "getting failed", null), HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getBankCustomers/{bankId}")
	public ResponseEntity<SuccessResponse> getBankCustomer(@PathVariable long bankId) {
		List<CustomerDto> bankCustomer = service.getBankCustomer(bankId);
		if (bankCustomer != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "succesfull", bankCustomer), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "failed", null), HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/updateBank/{bankId}")
	public ResponseEntity<SuccessResponse> updateBank(@RequestBody BankDto bankDto) {
		BankDto updateBank = service.updateBank(bankDto);
		if (updateBank != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "updated", updateBank), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/deleteBank/{bankId}")
	public ResponseEntity<SuccessResponse> deleteBank(@PathVariable long bankId) {
		BankDto deleteBank = service.deleteBank(bankId);
		if (deleteBank != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "deleted", deleteBank), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "not deleted", null), HttpStatus.BAD_REQUEST);
	}
}
