package com.appsdeveloperblog.rentacar.api.rental.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.appsdeveloperblog.rentacar.api.rental.business.abstracts.BrandService;
import com.appsdeveloperblog.rentacar.api.rental.business.constants.messages.BrandMessages;
import com.appsdeveloperblog.rentacar.api.rental.business.dtos.BrandSearchListDto;
import com.appsdeveloperblog.rentacar.api.rental.business.request.brandRequests.CreateBrandRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.brandRequests.DeleteBrandRequest;
import com.appsdeveloperblog.rentacar.api.rental.business.request.brandRequests.UpdateBrandRequest;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.business.BusinessRules;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.ErrorResult;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.Result;
import com.appsdeveloperblog.rentacar.api.rental.core.utilities.results.SuccessResult;
import com.appsdeveloperblog.rentacar.api.rental.dataAccess.abstracts.BrandDao;
import com.appsdeveloperblog.rentacar.api.rental.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrandManager implements BrandService {
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<BrandSearchListDto> getBrands() {
		
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream().map(brand -> modelMapperService.forDto()
				.map(brand, BrandSearchListDto.class)).collect(Collectors.toList());
		
		return response;
	}

	@Override
	public Result save(CreateBrandRequest createBrandRequest) {
		Result result = BusinessRules.run(checkExistingBrand(createBrandRequest.getBrandName()));
		
		if(result != null) {
			return result;
		}
		
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(BrandMessages.add);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);	
		return new SuccessResult(BrandMessages.delete);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
	Result result = BusinessRules.run(checkExistingBrand(updateBrandRequest.getBrandName()),
			checkExistingBrand(updateBrandRequest.getBrandId()));
		
		if(result != null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(BrandMessages.update);
	}
	
	private Result checkExistingBrand(String brandName1) {
		String lowerCaseBrandName = brandName1.toLowerCase();
		for (Brand brand : brandDao.findAll()) {
			String lowerCaseExistingBrandName = brand.getBrandName().toLowerCase();
			if(lowerCaseBrandName.equals(lowerCaseExistingBrandName)) {
				return new ErrorResult(BrandMessages.duplicationError);
			}
		}
		return new SuccessResult();
	}

	private Result checkExistingBrand(int brandId){
		boolean isExisting = brandDao.existsById(brandId);
		if (!isExisting){
			return new ErrorResult(BrandMessages.brandIdNotFound);
		}
		return new SuccessResult();
	}

}
