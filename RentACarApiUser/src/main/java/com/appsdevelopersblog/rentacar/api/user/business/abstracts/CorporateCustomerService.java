package com.appsdevelopersblog.rentacar.api.user.business.abstracts;

import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.DataResult;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;
import com.appsdevelopersblog.rentacar.api.user.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.dtos.CorporateCustomerSearchListDto;

import java.util.List;



public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomerSearchListDto>> getAll();
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	DataResult<CorporateCustomerSearchListDto> getById(String corporateCustomerId);
	
}
