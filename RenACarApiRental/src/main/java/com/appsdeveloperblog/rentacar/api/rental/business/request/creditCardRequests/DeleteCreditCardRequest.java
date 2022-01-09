package com.appsdeveloperblog.rentacar.api.rental.business.request.creditCardRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCreditCardRequest {
	@NotNull
	private int cardId;
}
