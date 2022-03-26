package com.bjuan.springbackend.service.implementation;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.User;
import com.bjuan.springbackend.repository.IUserRepository;
import com.bjuan.springbackend.service.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    @Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        // IDE doesn't complain but im not sure this is safe
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
    
}
