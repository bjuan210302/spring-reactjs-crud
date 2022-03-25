package com.bjuan.springbackend.repository;

import com.bjuan.springbackend.model.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdcutRepository extends CrudRepository<Product, Long>{
    
}
