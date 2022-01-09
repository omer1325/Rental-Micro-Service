package com.appsdevelopersblog.rentacar.api.user.business.abstracts;

import com.appsdevelopersblog.rentacar.api.user.business.requests.UserRequests.LoginUserRequest;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;
import com.appsdevelopersblog.rentacar.api.user.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	Result login(LoginUserRequest loginUserRequest);
	Result isUserExists(String userId);
	Result isUserEmailExists(String email);
	UserEntity getByEmail(String email);

}
