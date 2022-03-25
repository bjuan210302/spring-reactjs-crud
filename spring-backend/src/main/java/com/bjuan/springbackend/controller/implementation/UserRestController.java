package com.bjuan.springbackend.controller.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.controller.interfaces.IUserRestController;
import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.service.implementation.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestController implements IUserRestController{

    @Autowired
	private UserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        if (userService.findById(id).isPresent())
            return userService.findById(id).get();
        
        return null;
    }

    @GetMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        //TODO: Comprobations
        userService.save(user);
		return new ResponseEntity<User>(HttpStatus.OK);
    }

    @GetMapping("/del/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        Optional<User> userToDelete = userService.findById(id);

        if (userToDelete.isPresent()){
            userService.delete(userToDelete.get());
            return new ResponseEntity<User>(userToDelete.get(), HttpStatus.OK);
        }
        
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
