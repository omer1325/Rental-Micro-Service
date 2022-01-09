package com.appsdeveloperblog.rentacar.api.rental.core.utilities.mapping;


import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto();
	ModelMapper forRequest();
}
