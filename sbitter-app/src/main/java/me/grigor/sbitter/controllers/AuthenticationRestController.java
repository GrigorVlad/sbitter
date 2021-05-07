package me.grigor.sbitter.controllers;

import lombok.extern.slf4j.Slf4j;
import me.grigor.sbitter.dto.AuthenticationRequestDto;
import me.grigor.sbitter.dto.AuthorizedUserDto;
import me.grigor.sbitter.dto.RegistrationRequestDto;
import me.grigor.sbitter.dto.converters.UserConverter;
import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.response.ServiceResponse;
import me.grigor.sbitter.security.jwt.JwtTokenProvider;
import me.grigor.sbitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider jwtTokenProvider,
                                        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AuthorizedUserDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
        );

        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        AuthorizedUserDto authorizedUserDto = new AuthorizedUserDto(
                user.getId(),
                user.getUsername(),
                jwtTokenProvider.createToken(username),
                UserConverter.LIST_ROLES_CONVERTER.apply(user.getRoles())
        );

        return new ResponseEntity<>(authorizedUserDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<AuthorizedUserDto> register(@RequestBody RegistrationRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setFirstName(requestDto.getFirstName());
        user.setMiddleName(requestDto.getMiddleName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        ServiceResponse<User> register = userService.register(user);

        if (register.getData() == null) {
            return new ServiceResponse<>(register.getMessages());
        }

        User registeredUser = register.getData();
        AuthorizedUserDto authorizedUserDto = new AuthorizedUserDto(
                registeredUser.getId(),
                registeredUser.getUsername(),
                jwtTokenProvider.createToken(registeredUser.getUsername()),
                UserConverter.LIST_ROLES_CONVERTER.apply(registeredUser.getRoles())
        );
        return new ServiceResponse<>(
                authorizedUserDto,
                register.getMessages()
        );
    }
}
