package com.vikram.service;

import com.vikram.exception.CartItemException;
import com.vikram.exception.UserException;
import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	
}
