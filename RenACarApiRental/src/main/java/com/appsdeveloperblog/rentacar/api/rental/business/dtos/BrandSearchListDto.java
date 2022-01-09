package com.appsdeveloperblog.rentacar.api.rental.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BrandSearchListDto {

	private int brandId;
	private String brandName;


}