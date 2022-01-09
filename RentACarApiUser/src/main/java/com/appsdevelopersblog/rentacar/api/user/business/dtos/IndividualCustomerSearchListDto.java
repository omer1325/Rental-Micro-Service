package com.appsdevelopersblog.rentacar.api.user.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchListDto {
	
	private String id;
	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private String email;
	private String password;
	
	
}
