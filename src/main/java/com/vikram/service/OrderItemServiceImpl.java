package com.vikram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.model.OrderItems;
import com.vikram.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@Override
	public OrderItems createOrderItem(OrderItems orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
