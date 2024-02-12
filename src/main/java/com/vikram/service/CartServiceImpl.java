package com.vikram.service;

import com.vikram.exception.ProductException;
import com.vikram.model.Cart;
import com.vikram.model.User;
import com.vikram.repository.CartRepository;
import com.vikram.request.AddItemRequest;

public class CartServiceImpl implements CartService{
	
	private CartRepository cartRepository;
	
	private CartItemService cartItemService;

	@Override
	public Cart createCart(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findUserCart(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
