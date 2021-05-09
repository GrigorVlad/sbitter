package me.grigor.sbitter.controllers;

import me.grigor.sbitter.dto.UserDto;
import me.grigor.sbitter.dto.converters.UserConverter;
import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.services.UserConnectionService;
import me.grigor.sbitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestControllerV1 {

    private final UserService userService;
    private final UserConnectionService userConnectionService;

    @Autowired
    public UserRestControllerV1(UserService userService,
                                UserConnectionService userConnectionService) {
        this.userService = userService;
        this.userConnectionService = userConnectionService;
    }

    @RequestMapping(value = "/{userId}/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        User userById = userService.findById(userId);
        if (userById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                UserConverter.TO_USER_DTO.apply(userById),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/{userId}/followers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getUserFollower(@PathVariable Long userId) {
        List<User> userFollowers = userConnectionService.getUserFollowers(userId);
        return  userFollowers.stream()
                .map(UserConverter.TO_USER_DTO)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{userId}/followings", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getUserFollowing(@PathVariable Long userId) {
        List<User> userFollowing = userConnectionService.getUserFollowing(userId);
        return  userFollowing.stream()
                .map(UserConverter.TO_USER_DTO)
                .collect(Collectors.toList());
    }
}
