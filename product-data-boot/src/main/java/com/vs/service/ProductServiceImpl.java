package com.vs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vs.dto.ProductDto;
import com.vs.entity.Product;

import com.vs.repo.ProductRepository;
import com.vs.util.ProductNotFoundException;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;

	@Override
	public void save(ProductDto p) {
		Product prod= new Product();
		
		prod.setCode(p.getCode());
		prod.setName(p.getName());
		prod.setCategory(p.getCategory());
		prod.setPrice(p.getPrice());
		repo.save(prod);

	}

	@Override
	public List<Product> list() {
		
		return repo.findAll();
	}

	@Override
	public Product getByCode(int code) throws ProductNotFoundException {
		
		return repo.findById(code).orElseThrow(()-> new
				ProductNotFoundException("No product with code:"+code));
	}

	@Override
	public void delete(int code) throws ProductNotFoundException {
		if (!repo.existsById(code)) {
            throw new ProductNotFoundException("No product with code:"+code);
        }
		repo.deleteById(code);

	}

	@Override
	public List<Product> listByCategory(String cat) throws ProductNotFoundException {
	
		List<Product> prod= repo.findByCategory(cat);
		if (prod == null || prod.isEmpty()) {
	        throw new ProductNotFoundException("No product with category:"+cat);
	    }
		return prod;
	}

	@Override
	public List<Product> listByPriceRange(double min, double max) throws ProductNotFoundException {
		List<Product> prod= repo.findByPriceBetween(min, max);
		if (prod == null || prod.isEmpty()) {
	        throw new ProductNotFoundException("No product with given range");
	    }
		return prod;
	}

	@Override
	public List<Product> listByPriceLowToHigh() {
		
		return repo.findAllByOrderByPriceDesc();
	}
	
	

}
