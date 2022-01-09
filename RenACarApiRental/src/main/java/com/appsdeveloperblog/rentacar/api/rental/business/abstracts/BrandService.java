package com.appsdeveloperblog.rentacar.api.rental.business.abstracts;

import java.util.List;

import com.appsdeveloperblog.rentacar.api.rental.business.dtos.BrandSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.brandRequests.CreateBrandRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.brandRequests.DeleteBrandRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.brandRequests.UpdateBrandRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;

public interface BrandService {
	List<BrandSearchListDto> getBrands();
	Result save(CreateBrandRequest createBrandRequest);
	Result delete(DeleteBrandRequest deleteBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
}
