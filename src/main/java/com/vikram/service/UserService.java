package com.vikram.service;

import com.vikram.exception.UserException;
import com.vikram.model.User;

public interface UserService {

	public User findUserById(Long id) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
}
