package com.appsdevelopersblog.rentacar.api.user.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.appsdevelopersblog.rentacar.api.user.business.abstracts.CorporateCustomerService;
import com.appsdevelopersblog.rentacar.api.user.business.abstracts.UserService;
import com.appsdevelopersblog.rentacar.api.user.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.constants.Messages;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.business.BusinessRules;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.mapping.ModelMapperService;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.*;
import com.appsdevelopersblog.rentacar.api.user.dataAccess.abstracts.CorporateCustomerDao;
import com.appsdevelopersblog.rentacar.api.user.business.dtos.CorporateCustomerSearchListDto;
import com.appsdevelopersblog.rentacar.api.user.entities.CorporateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;



	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService, @Lazy UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.userService = userService;

	}

	@Override
	public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
		List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
		List<CorporateCustomerSearchListDto> response = result.stream().map(corporateCustomer -> modelMapperService
				.forDto().map(corporateCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response, Messages.CUSTOMERLIST);

	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		var result = BusinessRules.run(checkIsCorporateCustomerEmailExists(createCorporateCustomerRequest.getEmail()),checkCorporateTaxNumberExists(createCorporateCustomerRequest.getTaxNumber()));
		if (result!= null) {
			return result;
		}
		createCorporateCustomerRequest.setId(UUID.randomUUID().toString());
		createCorporateCustomerRequest.setEncryptedPassword(bCryptPasswordEncoder.encode(createCorporateCustomerRequest.getPassword()));
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERADD);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkCorporateCustomerExistsBuId(updateCorporateCustomerRequest.getId()));
		if (result != null) {
			return result;
		}
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERUPDATE);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkCorporateCustomerExistsBuId(deleteCorporateCustomerRequest.getCustomerId()));
		if (result != null) {
			return result;
		}
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(deleteCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERDELETE);
	}

	@Override
	public DataResult<CorporateCustomerSearchListDto> getById(String corporateCustomerId) {

		var existsIndividualCustomer = this.corporateCustomerDao.existsById(corporateCustomerId);
		if (!existsIndividualCustomer) {
			return new ErrorDataResult(Messages.CUSTOMERNOTFOUND);
		}
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);
		CorporateCustomerSearchListDto corporateCustomerSearchListDto = modelMapperService.forDto()
				.map(corporateCustomer, CorporateCustomerSearchListDto.class);
		return new SuccessDataResult<CorporateCustomerSearchListDto>(corporateCustomerSearchListDto,
				Messages.CUSTOMERGET);
	}

	private Result checkIsCorporateCustomerEmailExists(String email){
		var result = this.userService.isUserEmailExists(email);
		if(!result.isSuccess()){
			return new ErrorResult(Messages.CUSTOMERISALREADYEXISTS);
		}
		return new SuccessResult();		
	}
	private Result checkCorporateCustomerExistsBuId(String customerId) {
		var result = this.corporateCustomerDao.existsById(customerId);
		if (!result) {
			return new ErrorResult(Messages.CUSTOMERNOTFOUND);
		}
		return new SuccessResult();
	}


	private Result checkCorporateTaxNumberExists(String taxNumber){
		var result = this.corporateCustomerDao.existsByTaxNumber(taxNumber);
		if (result){
			return new ErrorResult(Messages.CUSTOMERTAXNUMBEREXISTS);
		}
		return new SuccessResult();
	}

}
