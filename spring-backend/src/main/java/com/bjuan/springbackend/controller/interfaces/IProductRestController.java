package com.bjuan.springbackend.controller.interfaces;

import java.util.List;

import com.bjuan.springbackend.model.Product;

import org.springframework.http.ResponseEntity;

public interface IProductRestController {

    public List<Product> getAll();
	
	public Product findById(Long id);

	public ResponseEntity<Product> save(Product product);

	public ResponseEntity<Product> delete(Long id) ;
}
