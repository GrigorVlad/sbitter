package me.grigor.sbitter.security;

import lombok.extern.slf4j.Slf4j;
import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.security.jwt.JwtUser;
import me.grigor.sbitter.security.jwt.JwtUserFactory;
import me.grigor.sbitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("[JwtUserDetailsService.loadUserByUsername] " +
                "User with username: {} was successfully loaded", username);
        return jwtUser;
    }
}
