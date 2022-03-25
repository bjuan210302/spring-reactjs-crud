package com.bjuan.springbackend.service.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.Product;
import com.bjuan.springbackend.repository.IProdcutRepository;
import com.bjuan.springbackend.service.interfaces.IProductService;

public class ProductService implements IProductService {
    
    private IProdcutRepository prodcutRepository;

    @Override
    public void save(Product product) {
        // TODO Auto-generated method stub
    }

    @Override
    public Optional<Product> findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Product product) {
        // TODO Auto-generated method stub
        
    }
    
}
