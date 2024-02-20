package com.vikram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.exception.CartItemException;
import com.vikram.exception.UserException;
import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.model.Product;
import com.vikram.model.User;
import com.vikram.response.ApiResponse;
import com.vikram.service.CartItemService;
import com.vikram.service.CartService;
import com.vikram.service.UserService;

@RestController
@RequestMapping("api/cartItems")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/create")
	public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem){
		
		CartItem createdCartItem = cartItemService.createCartItem(cartItem);
		
		return new ResponseEntity<CartItem>(createdCartItem, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, 
			@RequestHeader("Authorization") String jwt,
			@RequestBody CartItem cartItem) throws UserException, CartItemException{
		
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), id, cartItem);
		
		return new ResponseEntity<CartItem>(updatedCartItem, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/cartItem/{size}")
	public ResponseEntity<CartItem> isCartItemExist(@RequestBody Cart cart, 
		     @RequestBody Product product, @PathVariable String size,
			@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		CartItem cartItem = cartItemService.isCartItemExist(cart, product, size, user.getId());
		
		
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/cartItem/{cartItemId}")
	public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartItemId, 
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
		
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse response = new ApiResponse();
		response.setMessage("cartItem removed successfully");
		response.setStatus(true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/cartItem/{cartItemId}")
	public ResponseEntity<CartItem> findCartItemById(@PathVariable Long cartItemId) throws CartItemException{
		
		CartItem cartItem = cartItemService.findCartItemById(cartItemId);
		
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.ACCEPTED);
	}
	
}
