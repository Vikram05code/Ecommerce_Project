package com.vikram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.exception.ProductException;
import com.vikram.exception.UserException;
import com.vikram.model.Cart;
import com.vikram.model.User;
import com.vikram.request.AddItemRequest;
import com.vikram.response.ApiResponse;
import com.vikram.service.CartService;
import com.vikram.service.UserService;

import jakarta.persistence.TableGenerator;

@RestController
@RequestMapping("/api/cart")
//@Tag(name="Cart Management", description="find user cart, add item to cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	//@Operational(description="find cart by user id")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user.getId());
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		cartService.addCartItem(user.getId(), req);
		
		ApiResponse response = new ApiResponse();
		response.setMessage("item added to cart");
		response.setStatus(true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	
	
}
