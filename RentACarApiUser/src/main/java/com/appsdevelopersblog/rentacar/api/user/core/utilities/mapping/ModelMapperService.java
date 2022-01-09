package com.appsdevelopersblog.rentacar.api.user.core.utilities.mapping;


import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto();
	ModelMapper forRequest();
}
