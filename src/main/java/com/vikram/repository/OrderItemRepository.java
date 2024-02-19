package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.model.OrderItems;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long>{

}
