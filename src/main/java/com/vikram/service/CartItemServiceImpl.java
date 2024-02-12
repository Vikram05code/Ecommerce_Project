package com.vikram.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vikram.exception.CartItemException;
import com.vikram.exception.UserException;
import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.model.Product;
import com.vikram.repository.CartItemRepository;
import com.vikram.repository.CartRepository;

public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepository cartRepository;
	
	

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		// TODO Auto-generated method stub
		return null;
	}

}
