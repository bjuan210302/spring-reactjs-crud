package com.bjuan.springbackend.controller.interfaces;

import java.util.List;

import com.bjuan.springbackend.model.User;

import org.springframework.http.ResponseEntity;

public interface IUserRestController {

	public ResponseEntity<?> signup(User requestUser);

	public ResponseEntity<?> login(User requestUser);

    public ResponseEntity<List<User>> findAll();
	
	public ResponseEntity<?> findById(Long id);

	public ResponseEntity<?> delete(Long id) ;
}
