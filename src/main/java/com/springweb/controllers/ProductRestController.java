package com.springweb.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.entities.Product;
import com.springweb.repos.ProductRespository;



@RestController
@CacheConfig(cacheNames = "product")
public class ProductRestController {
	@Autowired
	ProductRespository repository;
	 @Autowired
	  private CacheManager cacheManager;
	private static final Logger  log =LoggerFactory.getLogger(ProductRestController.class);
	@RequestMapping(value="/products/list",method=RequestMethod.GET)
	@Transactional(readOnly = true)
	@Cacheable(value = "products")
	public List<Product> getProducts(){
		for (String cacheName : cacheManager.getCacheNames()) {
			log.warn("Clearing cache: " + cacheName);
		}
		return repository.findAll();
		
	}
	@Cacheable(value = "products", key = "#id")
	@RequestMapping (value="/products/get/{id}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable("id")int id) {
		log.info("you r in getProduct with id "+id);
		if(id==2) {
			log.error("Error occured.......");
			log.warn("Warning occured..........");
		}
		return repository.findById(id).get();
		
	}
	
	@CachePut(value = "products", key = "#product.id")
	@RequestMapping (value="/products/create",method=RequestMethod.POST)
	public Product createProduct(@RequestBody Product product){
		return repository.save(product);
	}
	
	@CachePut(value = "products", key = "#product.id")
	@RequestMapping (value="/products/update",method=RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product){
		return repository.save(product);
	}
	
	
	  
	  //@CacheEvict(value="products", key="#product.id") public void
	  @CacheEvict(value = "products", key = "#id")
	  @RequestMapping (value="/products/delete/{id}",method=RequestMethod.DELETE)
	  public void deleteProduct(@PathVariable("id")int id) { 
	  repository.deleteById(id);
	  }
	 
	
	

}
