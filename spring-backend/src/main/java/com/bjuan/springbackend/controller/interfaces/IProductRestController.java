package com.bjuan.springbackend.controller.interfaces;

import java.util.List;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.model.User;

import org.springframework.http.ResponseEntity;

public interface IProductRestController {

    public ResponseEntity<List<Product>> findAll(User owner);
	
	public ResponseEntity<Product> findById(Long id);

	public ResponseEntity<Product> save(Product product);

	public ResponseEntity<Product> delete(Product product) ;
}
