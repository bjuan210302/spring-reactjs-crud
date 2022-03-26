package com.bjuan.springbackend.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.bjuan.springbackend.model.User;

public interface IUserService {
    public void save(User user);

	public Optional<User> findById(long id);

	public List<User> findAll();

	public void delete(User user);

	public void deleteById(Long id);

	public void deleteAll();
}
