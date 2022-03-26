package com.bjuan.springbackend.service.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.repository.IProductRepository;
import com.bjuan.springbackend.service.interfaces.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    
    private IProductRepository productRepository;

    private UserService userService;

    @Autowired
	public ProductService(IProductRepository productRepository, UserService userService) {
		this.productRepository = productRepository;
        this.userService = userService;
	}

    @Override
    public void save(Product product) {
        Optional<User> owner = userService.findById(product.getOwnerId());
        if(owner.isEmpty())
            throw new IllegalArgumentException();

        // TODO: Check for duplicates (by name maybe?)
        productRepository.save(product);
        owner.get().addProduct(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByOwnerId(long id) {
        return productRepository.findByOwnerId(id);
    }

    @Override
    public void delete(Product product) {
        if(productRepository.findById(product.getId()).isEmpty())
            throw new IllegalArgumentException();
        
        Optional<User> owner = userService.findById(product.getOwnerId());
        if(owner.isEmpty())
            throw new IllegalArgumentException();

        owner.get().deleteProduct(product);
        productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
    
}
