package com.bjuan.springbackend.controller.interfaces;

import java.util.List;

import com.bjuan.springbackend.model.User;

import org.springframework.http.ResponseEntity;

public interface IUserRestController {

    public ResponseEntity<List<User>> findAll();
	
	public ResponseEntity<User> findById(Long id);

	public ResponseEntity<User> save(User user);

	public ResponseEntity<User> delete(Long id) ;
}
