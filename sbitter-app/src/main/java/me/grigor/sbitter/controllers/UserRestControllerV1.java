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

    @RequestMapping(value = "/{userId}/info/{personId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId, @PathVariable Long personId) {
        User person = userService.findById(personId);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDto userDto = UserConverter.TO_USER_DTO.apply(person);
        if (userId.equals(personId)) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }

        userDto.setFollower(userConnectionService.checkFollower(userId, personId));
        userDto.setFollowing(userConnectionService.checkFollowing(userId, personId));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
