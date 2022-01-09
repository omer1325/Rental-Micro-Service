package com.appsdevelopersblog.rentacar.api.user.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.appsdevelopersblog.rentacar.api.user.business.abstracts.IndividualCustomerService;
import com.appsdevelopersblog.rentacar.api.user.business.abstracts.UserService;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.CreateIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.DeleteIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.UpdateIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.constants.Messages;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.business.BusinessRules;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.mapping.ModelMapperService;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.*;
import com.appsdevelopersblog.rentacar.api.user.dataAccess.abstracts.IndividualCustomerDao;
import com.appsdevelopersblog.rentacar.api.user.business.dtos.IndividualCustomerSearchListDto;
import com.appsdevelopersblog.rentacar.api.user.entities.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService,@Lazy UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.userService = userService;
	}

	@Override
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		List<IndividualCustomer> result = this.individualCustomerDao.findAll();
		List<IndividualCustomerSearchListDto> response = result.stream().map(individualCustomer->modelMapperService.forDto().map(individualCustomer,IndividualCustomerSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response,Messages.CUSTOMERLIST);
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualRequest) {
		var result = BusinessRules.run(checkIsIndividualCustomerEmailExists(createIndividualRequest.getEmail()));
		if(result != null){
			return result;
		}
		createIndividualRequest.setEncryptedPassword(bCryptPasswordEncoder.encode(createIndividualRequest.getPassword()));
		createIndividualRequest.setId(UUID.randomUUID().toString());
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualRequest,IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CUSTOMERADD);
			}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualRequest) {
		Result result = BusinessRules.run(checkIsIndividualCustomerExists(deleteIndividualRequest.getId()));
		if(result != null){
			return result;
		}
		IndividualCustomer individualCustomer=modelMapperService.forRequest().map(deleteIndividualRequest, IndividualCustomer.class);
		this.individualCustomerDao.delete(individualCustomer);
		return new SuccessResult(Messages.CUSTOMERDELETE);
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualRequest) {
		Result result = BusinessRules.run(checkIsIndividualCustomerExists(updateIndividualRequest.getId()));
		if(result != null){
			return result;
		}
		updateIndividualRequest.setEncryptedPassword(bCryptPasswordEncoder.encode(updateIndividualRequest.getPassword()));
		IndividualCustomer individualCustomer=modelMapperService.forRequest().map(updateIndividualRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CUSTOMERUPDATE);
	}

	@Override
	public DataResult<IndividualCustomerSearchListDto> getByIndividualCustomerId(String individualCustomerId) {
		
		var existsIndividualCustomer = this.individualCustomerDao.existsById(individualCustomerId);
		if (!existsIndividualCustomer) {
			return new ErrorDataResult<IndividualCustomerSearchListDto>(null,Messages.CUSTOMERNOTFOUND);
		}
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(individualCustomerId);
		IndividualCustomerSearchListDto customerSearchListDto = modelMapperService.forDto().map(individualCustomer,IndividualCustomerSearchListDto.class);
			return new SuccessDataResult<IndividualCustomerSearchListDto>(customerSearchListDto,Messages.CUSTOMERGET);
	}



	private Result checkIsIndividualCustomerEmailExists(String email){
		var result = this.userService.isUserEmailExists(email);
		if(!result.isSuccess()){
			return new ErrorResult(Messages.CUSTOMERISALREADYEXISTS);
		}
		return new SuccessResult();
	}
	
	private Result checkIsIndividualCustomerExists(String id){
		var result = this.individualCustomerDao.existsById(id);
		if(!result){
			return new ErrorResult(Messages.CUSTOMERNOTFOUND);
		}
		return new SuccessResult();
	}
	

	}
