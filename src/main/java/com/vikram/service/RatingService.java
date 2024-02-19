package com.vikram.service;

import java.util.List;

import com.vikram.exception.ProductException;
import com.vikram.model.Rating;
import com.vikram.model.User;
import com.vikram.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;
	public List<Rating>getProductsRating(Long productId);

}
