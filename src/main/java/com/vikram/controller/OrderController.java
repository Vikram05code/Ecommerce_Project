package com.vikram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.exception.OrderException;
import com.vikram.exception.UserException;
import com.vikram.model.Address;
import com.vikram.model.Order;
import com.vikram.model.User;
import com.vikram.repository.AddressRepository;
import com.vikram.service.OrderService;
import com.vikram.service.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Order order = orderService.createOrder(user, shippingAddress);
		
		System.out.println("Order: "+order);
		
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Order>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Order> orders = orderService.usersOrderHistory(user.getId());
		
		return new ResponseEntity<List<Order>>(orders, HttpStatus.CREATED);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long orderId, 
			@RequestHeader("Authorization") String jwt) throws UserException, OrderException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Order order = orderService.findOrderById(orderId);
		
		return new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);
	}
	
}
