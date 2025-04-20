package com.dailypractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	private long customerId;
	private String firstName;
	private String lastName;
	private String accountType;
	private String mobileNumber;
	private long bankId;

}
