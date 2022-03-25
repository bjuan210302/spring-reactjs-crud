package com.bjuan.springbackend.controller.interfaces;

import java.util.List;

import com.bjuan.springbackend.model.User;

import org.springframework.http.ResponseEntity;

public interface IUserRestController {

    public List<User> findAll();
	
	public User findById(Long id);

	public ResponseEntity<User> save(User user);

	public ResponseEntity<User> delete(Long id) ;
}
