package com.bjuan.springbackend.repository;

import com.bjuan.springbackend.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>{
    
}
