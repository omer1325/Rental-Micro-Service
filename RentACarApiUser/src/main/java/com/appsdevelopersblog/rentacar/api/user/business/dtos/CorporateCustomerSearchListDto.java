package com.appsdevelopersblog.rentacar.api.user.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerSearchListDto {
	
	private String id;
	private String companyName;
	private String taxNumber;
	private String email;
	private String password;

}
