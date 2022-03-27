package com.bjuan.springbackend.controller.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.controller.interfaces.IProductRestController;
import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/api/v1/products/")
public class ProductRestController implements IProductRestController{

    @Autowired
	private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<List<Product>> findAll(@RequestBody User owner) {
        // Safe to assume the user exists because this comes from the logged user id.
        return new ResponseEntity<List<Product>>(productService.findByOwnerId(owner.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        if (productService.findById(id).isPresent())
            return new ResponseEntity<Product>(productService.findById(id).get(), HttpStatus.OK);
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
		return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Product> delete(@RequestBody Product product) {
        if((Long)product.getId() == null || product.getOwner() == null)
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        
        try{
            Optional<Product> prod = productService.findById(product.getId());
            productService.delete(product);
            
            Product res = prod.get();
            return new ResponseEntity<Product>(res, HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
