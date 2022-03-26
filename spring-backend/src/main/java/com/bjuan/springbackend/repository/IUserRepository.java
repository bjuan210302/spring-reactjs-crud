package com.bjuan.springbackend.repository;

import java.util.List;

import com.bjuan.springbackend.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>{
    
    // If i understand correctly, if this returns a list, the query is automatic 
    List<User> findByEmail(String email);
}
