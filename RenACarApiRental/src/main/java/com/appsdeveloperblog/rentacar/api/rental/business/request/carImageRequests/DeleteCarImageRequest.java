package com.appsdeveloperblog.rentacar.api.rental.business.request.carImageRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarImageRequest {

	@NotNull
	private int imageId;
}
