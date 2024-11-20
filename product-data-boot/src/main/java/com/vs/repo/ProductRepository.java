package com.vs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vs.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
//	@Query("FROM Product WHERE price BETWEEN :min AND :max")
//	List<Product> findByPriceRange(double min,double max);
	
	
//	@Query("FROM Product ORDER BY price desc")
//	List<Product> findByPriceDesc();
	
	List<Product> findByPriceBetween(double min, double max);
	
	List<Product> findByCategory(String category);
	
	List<Product> findAllByOrderByPriceDesc();
	
	

}
