package com.bjuan.springbackend.service.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.repository.IUserRepository;
import com.bjuan.springbackend.service.interfaces.IUserService;

import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<User> findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(User user) {
        // TODO Auto-generated method stub
        
    }
    
}
