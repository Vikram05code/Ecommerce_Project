package com.vikram.service;

import com.vikram.exception.ProductException;
import com.vikram.model.Cart;
import com.vikram.model.User;
import com.vikram.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
	
}
