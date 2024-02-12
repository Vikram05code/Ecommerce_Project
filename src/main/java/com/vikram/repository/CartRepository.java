package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	

}
