package com.vikram.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vikram.exception.ProductException;
import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.model.Product;
import com.vikram.model.User;
import com.vikram.repository.CartItemRepository;
import com.vikram.repository.CartRepository;
import com.vikram.request.AddItemRequest;

public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	pri

	@Override
	public Cart createCart(User user) {
		Cart cart =new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price =req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}
		return "Item Added To The Cart ";
	}

	@Override
	public Cart findUserCart(Long userId) {
		
		Cart cart = cartRepository.findByUserId(userId);
		
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		
		for(CartItem cartItem : cart.getCartItems()) {
			totalPrice += cartItem.getPrice();
			totalDiscountedPrice += cartItem.getDiscountedPrice();
			totalItem += cartItem.getQuantity();
			
		}
		
		cart.setTotalItem(totalItem);
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice - totalDiscountedPrice);
		
		return cartRepository.save(cart);
	}

}
