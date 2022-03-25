package com.bjuan.springbackend.controller.implementation;

import java.util.List;

import com.bjuan.springbackend.controller.interfaces.IProductRestController;
import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.service.implementation.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class ProductRestController implements IProductRestController{

    @Autowired
	private ProductService productService;

    public List<Product> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Product> save(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Product> delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
