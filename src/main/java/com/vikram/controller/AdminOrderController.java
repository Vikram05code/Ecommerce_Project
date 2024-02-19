package com.vikram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.exception.OrderException;
import com.vikram.model.Order;
import com.vikram.response.ApiResponse;
import com.vikram.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrdersHandler(){
		
		List<Order> orders = orderService.getAllOrders();
		
		return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order> confirmedOrderHandler(@PathVariable Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException {
		
		Order order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException {
		
		Order order = orderService.shippedOrder(orderId);
		
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order> deliveredOrderHandler(@PathVariable Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException {
		
		Order order = orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrderHandler(@PathVariable Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException {
		
		Order order = orderService.canceledOrder(orderId);
		
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId, 
			@RequestHeader("Authorization") String jwt) throws OrderException {
		
		orderService.deleteOrder(orderId);
		
		ApiResponse response = new ApiResponse();
		response.setMessage("order deleted successfully");
		response.setStatus(true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	
	
	
	
}
