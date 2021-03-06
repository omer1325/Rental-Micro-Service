package com.appsdeveloperblog.rentacar.api.rental.business.request.userRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {
	@NotNull
	private int userId;
}
