package me.grigor.sbitter.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.grigor.sbitter.entity.Role;
import me.grigor.sbitter.entity.Status;
import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.repository.RoleRepository;
import me.grigor.sbitter.repository.UserRepository;
import me.grigor.sbitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
//                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role role = roleRepository.findByName("ROLE_USER");

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(role));
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("[UserServiceImpl, method: register] User: {} successfully created", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("[UserServiceImpl, method: getAll] {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("[UserServiceImpl, method: findByUsername] User: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long userId) {
        User result = userRepository.findById(userId).orElse(null);
        if (result == null) {
            log.warn("[UserServiceImpl, method: findById] No user was found by id: {}", userId);
            return null;
        }
        log.info("[UserServiceImpl, method: findById] User: {} found by id: {}", result, userId);
        return result;
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
        log.info("[UserServiceImpl, method: deleteById] User with id: {} was successfully deleted", userId);
    }
}
