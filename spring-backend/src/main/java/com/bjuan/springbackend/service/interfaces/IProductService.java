package com.bjuan.springbackend.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.Product;

public interface IProductService {
    public void save(Product product);

	public Optional<Product> findById(long id);

	public List<Product> findByOwnerId(long id);

	public void delete(Product product);

	public void deleteAll();
}
