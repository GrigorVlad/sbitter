package me.grigor.sbitter.services;

import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.response.ServiceResponse;

import java.util.List;

public interface UserService {

    ServiceResponse<User> register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long userId);

    void deleteById(Long userId);
}
