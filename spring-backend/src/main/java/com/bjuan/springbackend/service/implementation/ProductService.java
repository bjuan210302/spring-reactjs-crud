package com.bjuan.springbackend.service.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.repository.IProdcutRepository;
import com.bjuan.springbackend.service.interfaces.IProductService;

import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    
    private IProdcutRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
    
}
