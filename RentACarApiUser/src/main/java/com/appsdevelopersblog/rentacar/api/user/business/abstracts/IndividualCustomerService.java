package com.appsdevelopersblog.rentacar.api.user.business.abstracts;

import java.util.List;

import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.CreateIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.DeleteIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.UpdateIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.DataResult;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;
import com.appsdevelopersblog.rentacar.api.user.business.dtos.IndividualCustomerSearchListDto;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerSearchListDto>> getAll();

	Result add(CreateIndividualCustomerRequest createIndividualRequest);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualRequest);

	DataResult<IndividualCustomerSearchListDto> getByIndividualCustomerId(String individualCustomerId);

}
