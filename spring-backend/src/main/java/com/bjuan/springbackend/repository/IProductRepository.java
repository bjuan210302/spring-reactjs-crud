package com.bjuan.springbackend.repository;

import java.util.List;

import com.bjuan.springbackend.model.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long>{
    
    List<Product> findByOwnerId(long id);
}
