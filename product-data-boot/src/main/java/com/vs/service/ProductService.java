package com.vs.service;

import java.util.List;

import com.vs.dto.ProductDto;
import com.vs.entity.Product;
import com.vs.util.ProductNotFoundException;


public interface ProductService {
	
	void save(ProductDto p);
	
	List<Product> list();
	
	Product getByCode(int code) throws ProductNotFoundException;
	
	void delete(int code) throws ProductNotFoundException;
	
	List<Product> listByCategory(String cat) throws ProductNotFoundException;
	
	List<Product> listByPriceRange(double min, double max) throws ProductNotFoundException;
	
	List<Product> listByPriceLowToHigh();

}
