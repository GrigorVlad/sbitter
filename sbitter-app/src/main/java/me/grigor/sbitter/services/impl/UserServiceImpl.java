package me.grigor.sbitter.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.grigor.sbitter.entity.Role;
import me.grigor.sbitter.entity.Status;
import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.repository.RoleRepository;
import me.grigor.sbitter.repository.UserRepository;
import me.grigor.sbitter.response.ResultMessages;
import me.grigor.sbitter.response.ServiceResponse;
import me.grigor.sbitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private static final String USERNAME_KEY = "username";
    private static final String EMAIL_KEY = "email";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment environment;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           Environment environment) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public ServiceResponse<User> register(User user) {
        ResultMessages resultMessages = new ResultMessages();
        if(userRepository.existsByUsername(user.getUsername())) {
            resultMessages.addError(USERNAME_KEY, environment.getProperty("error.exists.username"));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            resultMessages.addInfos(EMAIL_KEY, environment.getProperty("error.exists.email"));
        }

        if (resultMessages.errorsExists()) {
            return new ServiceResponse<>(resultMessages);
        }

        Role role = roleRepository.findByName("ROLE_USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(role));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("[UserServiceImpl.register] User: {} successfully created", registeredUser);
        return new ServiceResponse<>(registeredUser, resultMessages);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("[UserServiceImpl.getAll] {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("[UserServiceImpl.findByUsername] User: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long userId) {
        User result = userRepository.findById(userId).orElse(null);
        if (result == null) {
            log.warn("[UserServiceImpl.findById] No user was found by id: {}", userId);
            return null;
        }
        log.info("[UserServiceImpl.findById] User: {} found by id: {}", result, userId);
        return result;
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
        log.info("[UserServiceImpl.deleteById] User with id: {} was successfully deleted", userId);
    }
}
