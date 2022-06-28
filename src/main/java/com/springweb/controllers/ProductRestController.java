package com.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.entities.Product;
import com.springweb.repos.ProductRespository;

@RestController

public class ProductRestController {
	@Autowired
	ProductRespository repository;
	@RequestMapping(value="/products/list",method=RequestMethod.GET)
	public List<Product> getProducts(){
		return repository.findAll();
		
	}
	@RequestMapping (value="/products/get/{id}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable("id")int id) {
		return repository.findById(id).get();
		
	}
	@RequestMapping (value="/products/create",method=RequestMethod.POST)
	public Product createProduct(@RequestBody Product product){
		return repository.save(product);
	}
	@RequestMapping (value="/products/update",method=RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product){
		return repository.save(product);
	}
	@RequestMapping (value="/products/delete/{id}",method=RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id")int id) {
		 repository.deleteById(id);
		
	}
	
	

}
