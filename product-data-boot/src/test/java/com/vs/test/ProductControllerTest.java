package com.vs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vs.dto.ProductDto;
import com.vs.entity.Product;
import com.vs.repo.ProductRepository;
import com.vs.service.ProductService;
import com.vs.util.ProductNotFoundException;

@SpringBootTest
public class ProductControllerTest {

	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repo;
	
	@BeforeEach
	public void setUp() {
	       
		 repo.deleteAll();
	      
	}
	
	@Test
	public void testSaveProduct() {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		service.save(p);
		
		List<Product> prod= repo.findAll();
		assertEquals(1, prod.size());
        assertEquals("Tablet", prod.get(0).getName());
        assertEquals(p.getCode(), prod.get(0).getCode());  
		
	}
	
	@Test
	public void testFindProduct() throws ProductNotFoundException {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		Product pr= new Product();
		pr.setCode(p.getCode());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setCategory(p.getCategory());
		
		repo.save(pr);
		
		Product prod=service.getByCode(pr.getCode());
	    assertNotNull(prod);
        assertEquals("Tablet", prod.getName());
        assertEquals(p.getCode(), prod.getCode());  
		
	}
	
	@Test
	public void testFindProduct_NotFound() {
		
		assertThrows(ProductNotFoundException.class, () ->{
			service.getByCode(999);
		}) ;
		
	}
	
	@Test
	public void testListProduct() {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		Product pr= new Product();
		pr.setCode(p.getCode());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setCategory(p.getCategory());
		
		
		ProductDto q = new ProductDto();
		q.setCode(102);
		q.setName("ToothPaste");
		q.setPrice(100);
		q.setCategory("Grocery");
		
		Product pr1= new Product();
		pr1.setCode(q.getCode());
		pr1.setName(q.getName());
		pr1.setPrice(q.getPrice());
		pr1.setCategory(q.getCategory());
		
		repo.save(pr);
		repo.save(pr1);
		
		
		List<Product> prod= service.list();
		assertEquals(2, prod.size());
         
		
	}
	
	@Test
	public void testRemoveProduct() throws ProductNotFoundException {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		Product pr= new Product();
		pr.setCode(p.getCode());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setCategory(p.getCategory());
		
		repo.save(pr);
		
		service.delete(pr.getCode());
		
		
		assertFalse(repo.existsById(pr.getCode()));
        
		
	}
	
	@Test
	public void testListProductByCategory() throws ProductNotFoundException {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		Product pr= new Product();
		pr.setCode(p.getCode());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setCategory(p.getCategory());
		
		repo.save(pr);
		
		
		
		List<Product> prod= service.listByCategory(pr.getCategory());
		assertEquals(1, prod.size());
		 assertEquals("Medicine", pr.getCategory());
         
		
	}
	
	@Test
	public void testListProductByPrice() throws ProductNotFoundException {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		Product pr= new Product();
		pr.setCode(p.getCode());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setCategory(p.getCategory());
		
		ProductDto q = new ProductDto();
		q.setCode(102);
		q.setName("ToothPaste");
		q.setPrice(100);
		q.setCategory("Grocery");
		
		Product pr1= new Product();
		pr1.setCode(q.getCode());
		pr1.setName(q.getName());
		pr1.setPrice(q.getPrice());
		pr1.setCategory(q.getCategory());
		
		repo.save(pr);
		repo.save(pr1);
		
		
		
		List<Product> prod= service.listByPriceRange(99, 600);
		assertEquals(2, prod.size());
		
	}
	
	@Test
	public void testListProductPriceHighToLow() throws ProductNotFoundException {
		
		ProductDto p = new ProductDto();
		p.setCode(101);
		p.setName("Tablet");
		p.setPrice(500);
		p.setCategory("Medicine");
		
		Product pr= new Product();
		pr.setCode(p.getCode());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setCategory(p.getCategory());
		
		ProductDto q = new ProductDto();
		q.setCode(102);
		q.setName("ToothPaste");
		q.setPrice(100);
		q.setCategory("Grocery");
		
		Product pr1= new Product();
		pr1.setCode(q.getCode());
		pr1.setName(q.getName());
		pr1.setPrice(q.getPrice());
		pr1.setCategory(q.getCategory());
		
		repo.save(pr);
		repo.save(pr1);
		
		
		
		List<Product> prod= service.listByPriceLowToHigh();
		assertEquals(2, prod.size());
		assertEquals(500, pr.getPrice());
		assertEquals(100, pr1.getPrice());
		
	}
	
	
}
