package com.appsdeveloperblog.rentacar.api.rental.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchListDto {
	
	private int userId;

	private String eMail;
	
	private String password;
	
}
