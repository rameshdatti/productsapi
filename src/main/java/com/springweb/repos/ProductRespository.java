package com.springweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springweb.entities.Product;

public interface ProductRespository extends JpaRepository<Product, Integer> {

}
