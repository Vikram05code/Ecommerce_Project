package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
