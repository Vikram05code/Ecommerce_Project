package com.vikram.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vikram.exception.ProductException;
import com.vikram.model.Category;
import com.vikram.model.Product;
import com.vikram.request.CreateProductRequest;

public interface ProductService {

	  public Product createProduct(CreateProductRequest req);
	  
	  public String deleteProduct(Long productId)throws ProductException;
	  
	  public Product updateProduct(Long productId, Product reqProduct)throws ProductException;
	  
	  public Product findProductById(Long id) throws ProductException;
	  
	  public List<Product> findProductByCategory(String category);
	  
	  public Page<Product> getAllProduct(String category, List<String>colors, List<String>sizes, Integer minPrice, Integer maxPrice,
			  Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	  
	  public List<Product> findAllProducts();
	  
	  
	  
	
}
