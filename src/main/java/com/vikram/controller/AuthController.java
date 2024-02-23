package com.vikram.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.config.JwtProvider;
import com.vikram.exception.UserException;
import com.vikram.model.Cart;
import com.vikram.model.User;
import com.vikram.repository.UserRepository;
import com.vikram.request.LoginRequest;
import com.vikram.response.AuthResponse;
import com.vikram.service.CartService;
import com.vikram.service.CustomUserServiceImplementation;

import io.jsonwebtoken.security.Message;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserServiceImplementation customUserServiceImplementation;

	@Autowired
	private CartService cartService;
	
	
	
	@PostMapping("/signup")
	 public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		 
		 String email =user.getEmail();
		 String password = user.getPassword();
		 String firstName = user.getFirstName();
		 String lastName = user.getLastName();
		 
		 User isEmailExist = userRepository.findByEmail(email);
		 
		 if(isEmailExist != null) {
			 throw new UserException("Email is already used with another account");
		 }
		 
		 User createdUser = new User();
		 
		 createdUser.setEmail(email);
		 createdUser.setPassword(passwordEncoder.encode(password));
		 createdUser.setFirstName(firstName);
		 createdUser.setLastName(lastName);
		 
		 User savedUser = userRepository.save(createdUser);
		 
		 Cart cart = cartService.createCart(savedUser);
		 
		 Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		 
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		 String token = jwtProvider.generateToken(authentication);
		 
		 AuthResponse authResponse = new AuthResponse();
		 authResponse.setJwt(token);
		 authResponse.setMessage("Signup successfully");
		 
		 return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
		 
	 }
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		 
		AuthResponse authResponse = new AuthResponse();
		 authResponse.setJwt(token);
		 authResponse.setMessage("Signin successfully");
		 
		 return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Inavalid Password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
	 
	 
}
