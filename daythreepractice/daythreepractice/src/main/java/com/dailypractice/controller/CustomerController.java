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

import com.dailypractice.dto.CustomerDto;
import com.dailypractice.response.SuccessResponse;
import com.dailypractice.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;

	@PostMapping("/addCustomer")
	public ResponseEntity<SuccessResponse> add(@RequestBody CustomerDto customerDto) {
		CustomerDto add = service.add(customerDto);
		if (add != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "added", add), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "failed", null), HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getCustomer")
	public ResponseEntity<SuccessResponse> getCustomer() {
		List<CustomerDto> customer = service.getCustomer();
		if (customer.isEmpty()) {
			return new ResponseEntity<>(new SuccessResponse(true, "getting failed", null), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new SuccessResponse(false, "getting successfull", customer), HttpStatus.OK);

	}

	@GetMapping("/getCustome/{customerId}")
	public ResponseEntity<SuccessResponse> getCustomerById(@PathVariable long customerId) {
		CustomerDto byId = service.getById(customerId);
		if (byId != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "get successfully", byId), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "getting failed", null), HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<SuccessResponse> updateCustomer(@RequestBody CustomerDto customerDto) {
		CustomerDto updateCustomer = service.updateCustomer(customerDto);
		if (updateCustomer != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "updated", updateCustomer), HttpStatus.OK);
		}

		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable long customerId) {
		CustomerDto deleteCustomer = service.deleteCustomer(customerId);
		if (deleteCustomer != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "deleted", deleteCustomer), HttpStatus.OK);
		}

		return new ResponseEntity<>(new SuccessResponse(true, "deletion failed", null), HttpStatus.BAD_REQUEST);

	}

}
