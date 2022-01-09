package com.appsdevelopersblog.rentacar.api.user.controllers;

import java.util.List;

import javax.validation.Valid;

import com.appsdevelopersblog.rentacar.api.user.business.abstracts.IndividualCustomerService;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.CreateIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.DeleteIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.business.requests.individualRequests.UpdateIndividualCustomerRequest;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.DataResult;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;
import com.appsdevelopersblog.rentacar.api.user.business.dtos.IndividualCustomerSearchListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {

	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@GetMapping("getAll")
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		return this.individualCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@GetMapping("/getByIndividualCustomerById")
	public DataResult<IndividualCustomerSearchListDto> getByIndividualCustomerById(@RequestParam("id") String id) {
		return this.individualCustomerService.getByIndividualCustomerId(id);
	}

}
