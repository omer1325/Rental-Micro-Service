package com.appsdeveloperblog.rentacar.api.rental.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarSearchListDto {

	private String carId;
	private int brandId;
	private int colorId;
	private String modelYear;
	private double dailyPrice;

}