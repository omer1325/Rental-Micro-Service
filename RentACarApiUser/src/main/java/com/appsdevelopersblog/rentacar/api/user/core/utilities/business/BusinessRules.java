package com.appsdevelopersblog.rentacar.api.user.core.utilities.business;


import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;

public class BusinessRules {
	public static Result run(Result ...logics) {
		for(Result logic : logics) {
			if(!logic.isSuccess()) {
				return logic;
			}
		}
		return null;
	}
}
