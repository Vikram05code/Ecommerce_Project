package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
