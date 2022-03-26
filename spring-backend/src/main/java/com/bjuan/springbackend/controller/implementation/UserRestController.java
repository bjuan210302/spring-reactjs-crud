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
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User requestUser) {

        Optional<User> candidate = validateAndCreate(requestUser);
        if(candidate.isEmpty())
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        
        User newUser = candidate.get();
        try{
            userService.save(newUser);
            return new ResponseEntity<User>(newUser, HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User requestUser) {
        // The logic here is that, if the password is correct, a complete object
        // of the user is returned. With that the browser has access to the user ID, which
        // is required to do CRUD operations.

        Optional<User> databaseUser = userService.findByEmail(requestUser.getEmail());

        if(databaseUser.isEmpty())
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        
        // Cutting-edge security
        if(!databaseUser.get().getPassword().equals(requestUser.getPassword()))
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<User>(databaseUser.get(), HttpStatus.OK);
    }

    public Optional<User> validateAndCreate(User requestUser){
        // Probably will delete this. Front should check that user input is not null
        // and make validations for name, surname length and email, password structure
        boolean flag = true;
        
        flag = flag & requestUser.getName() != null;
        flag = flag & requestUser.getSurname() != null;
        flag = flag & requestUser.getEmail() != null;
        flag = flag & requestUser.getPassword() != null;

        if(!flag)
            return Optional.empty();

        User newUser = new User();
        newUser.setName(requestUser.getName());
        newUser.setSurname(requestUser.getSurname());
        newUser.setEmail(requestUser.getEmail());
        newUser.setPassword(requestUser.getPassword());
        
        return Optional.of(newUser);
    }
    // Don't really know if ill need this

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
}
