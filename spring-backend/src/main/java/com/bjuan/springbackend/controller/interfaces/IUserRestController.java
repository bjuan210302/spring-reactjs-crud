package com.bjuan.springbackend.controller.interfaces;

import java.util.List;

import com.bjuan.springbackend.model.User;

import org.springframework.http.ResponseEntity;

public interface IUserRestController {

	public ResponseEntity<User> signup(User requestUser);

	public ResponseEntity<User> login(User requestUser);

    public ResponseEntity<List<User>> findAll();
	
	public ResponseEntity<User> findById(Long id);

	public ResponseEntity<User> delete(Long id) ;
}
