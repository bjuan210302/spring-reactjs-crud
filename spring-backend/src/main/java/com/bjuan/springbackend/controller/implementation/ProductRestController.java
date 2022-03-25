package com.bjuan.springbackend.controller.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.controller.interfaces.IProductRestController;
import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.service.implementation.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/products/")
public class ProductRestController implements IProductRestController{

    @Autowired
	private ProductService productService;

    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @Override
    public Product findById(@PathVariable("id") Long id) {
        if (productService.findById(id).isPresent())
            return productService.findById(id).get();
        return null;
    }

    @Override
    public ResponseEntity<Product> save(@RequestBody Product product) {
        //TODO: Comprobations
        productService.save(product);
		return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> delete(Long id) {
        Optional<Product> productToDelete = productService.findById(id);

        if (productToDelete.isPresent()){
            productService.delete(productToDelete.get());
            return new ResponseEntity<Product>(productToDelete.get(), HttpStatus.OK);
        }
        
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
}
