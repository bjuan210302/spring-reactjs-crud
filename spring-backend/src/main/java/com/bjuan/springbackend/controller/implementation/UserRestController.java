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

    // I haven't really used ResponseEntity, but I suppose it is ok to keep
    // returns consistent across class, and then only check for body if HttpStatus == OK

    @Autowired
	private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<List<User>>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        if (userService.findById(id).isPresent())
            return new ResponseEntity<User>(userService.findById(id).get(), HttpStatus.OK);
        
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        try{
            userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/del/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        try{
            Optional<User> user = userService.findById(id);
            userService.deleteById(id);
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

}
