package com.appsdeveloperblog.rentacar.api.rental.business.request.rentingBillRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentingBillRequest {
	private int billId;
}
