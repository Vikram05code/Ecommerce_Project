package com.vikram.service;

import java.util.List;

import com.vikram.exception.ProductException;
import com.vikram.model.Review;
import com.vikram.model.User;
import com.vikram.request.ReviewRequest;

public interface ReviewService {

	public Review cerateReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
}
