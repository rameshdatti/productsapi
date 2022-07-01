package com.springweb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springweb.entities.Product;
import com.springweb.repos.ProductRespository;

@Controller
public class ThymeleafDemoController {
	@Autowired
	ProductRespository repository;
	
	@RequestMapping("/formDetails/{id}")
	public ModelAndView form(@PathVariable("id")int id) {
		ModelAndView mav=new ModelAndView("form");
		mav.addObject("uname","rameshdatti");
		mav.addObject("upass","ramesh");
		mav.addObject("paraData", "This is the para data");
		Optional<Product> product=repository.findById(id);
		
		mav.addObject("productData", product);
		return mav;
	}
	
	@RequestMapping("/products")
	public ModelAndView productsMethod() {
		ModelAndView mav=new ModelAndView("products-list");
		List<Product> products = repository.findAll();
		mav.addObject("productsData", products);
		return mav;
	}
	
	@RequestMapping("/productForm")
	public ModelAndView productForm1() {
		ModelAndView mav=new ModelAndView("product-form");
		Product product=new Product();
		mav.addObject("productData", product);
		return mav;
	}
	
	@RequestMapping("/saveProduct")
	public ModelAndView saveProduct(@ModelAttribute Product product) {
		repository.save(product);
		ModelAndView mav=new ModelAndView("result");
		return mav;
	}
}
