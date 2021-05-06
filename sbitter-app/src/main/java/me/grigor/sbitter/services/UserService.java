package me.grigor.sbitter.services;

import me.grigor.sbitter.entity.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long userId);

    void deleteById(Long userId);
}
