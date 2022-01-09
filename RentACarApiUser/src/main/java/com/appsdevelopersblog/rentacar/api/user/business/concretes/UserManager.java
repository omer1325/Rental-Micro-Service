package com.appsdevelopersblog.rentacar.api.user.business.concretes;

import com.appsdevelopersblog.rentacar.api.user.business.abstracts.UserService;
import com.appsdevelopersblog.rentacar.api.user.business.requests.UserRequests.LoginUserRequest;
import com.appsdevelopersblog.rentacar.api.user.business.constants.Messages;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.business.BusinessRules;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.mapping.ModelMapperService;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.ErrorResult;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.Result;
import com.appsdevelopersblog.rentacar.api.user.core.utilities.results.SuccessResult;
import com.appsdevelopersblog.rentacar.api.user.dataAccess.abstracts.UserDao;
import com.appsdevelopersblog.rentacar.api.user.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserManager implements UserService {
	private UserDao userDao;
	private ModelMapperService modelMapperService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Result login(LoginUserRequest loginUserRequest) {
		Result result = BusinessRules.run(checkCredentials(loginUserRequest.getEmail(), loginUserRequest.getPassword()));
		if (result!=null) {
			return result;
		}
		return new SuccessResult(Messages.LOGINSUCCESS);
	}
	private Result checkCredentials(String email,String password) {
		var user =  this.userDao.getByEmail(email);
		if (user == null) {
			return new ErrorResult(Messages.LOGINEMAILERROR);
		}
		var result= bCryptPasswordEncoder.matches(password,user.getEncryptedPassword());
		if (!result) {
			return new ErrorResult(Messages.LOGINPASSWORDERROR+" "+result);
		}
		return new SuccessResult();	
	}
	@Override
	public Result isUserExists(String userId) {
		var result=this.userDao.existsById(userId);
		if (!result) {
			return new ErrorResult(Messages.USERNOTFOUND);
		}
		return new SuccessResult();
	}

	@Override
	public Result isUserEmailExists(String email) {
		var existsResult = this.userDao.existsUserByEmail(email);
		if (existsResult){
			return new ErrorResult(Messages.EMAILERROR);
		}
		return new SuccessResult();
	}

	@Override
	public UserEntity getByEmail(String email) {
		UserEntity userEntity  = this.userDao.getByEmail(email);
		if (userEntity == null){
			throw new UsernameNotFoundException(email);
		}
		return userEntity;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserEntity userEntity = this.userDao.getByEmail(username);
			if (userEntity == null){
				throw new UsernameNotFoundException(username);
			}
			return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
		}
	}

