package com.appsdevelopersblog.rentacar.api.user.controllers;

import com.appsdevelopersblog.rentacar.api.user.business.abstracts.UserService;
import com.appsdevelopersblog.rentacar.api.user.business.requests.UserRequests.LoginUserRequest;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UsersController {
	private UserService userService;
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/status/check")
	public String status(){
		return "Working on";
	}


	/*@PostMapping("login")
	public Result login(@RequestBody LoginUserRequest loginUserRequest){
		return this.userService.login(loginUserRequest);
	}*/
	
	
	
	
	
	
	
	
	
	
	
}
