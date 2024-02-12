package com.vikram.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.config.JwtProvider;
import com.vikram.exception.UserException;
import com.vikram.model.User;
import com.vikram.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("User Not Found with id : "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
	
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not find with email : "+email);
		}
		
		return user;
	}

}
