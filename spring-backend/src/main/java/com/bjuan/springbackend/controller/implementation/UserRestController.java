package com.bjuan.springbackend.controller.implementation;

import java.util.List;

import com.bjuan.springbackend.controller.interfaces.IUserRestController;
import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserRestController implements IUserRestController{

    @Autowired
	private UserService userService;

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<User> save(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<User> delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
