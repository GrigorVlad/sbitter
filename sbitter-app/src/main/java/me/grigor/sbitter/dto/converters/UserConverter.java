package me.grigor.sbitter.dto.converters;

import me.grigor.sbitter.dto.AuthorizedUserDto;
import me.grigor.sbitter.dto.UserDto;
import me.grigor.sbitter.entity.Role;
import me.grigor.sbitter.entity.User;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserConverter {
    //    private Long id;
    //    private String username;
    //    private String firstName;
    //    private String middleName;
    //    private String lastName;
    //    private String email;
    //    private List<String> roles;
    //    private Date created;
    //    private Date updated;

    public static final Function<Role, String> ROLE_CONVERTER = Role::getName;

    public static final Function<List<Role>, List<String>> LIST_ROLES_CONVERTER = (roles) -> {
        return roles.stream()
                .map(ROLE_CONVERTER)
                .collect(Collectors.toList());
    };

    public static final Function<User, UserDto> TO_USER_DTO = (user) -> {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setCreated(user.getCreated());
        dto.setUpdated(user.getUpdated());
        dto.setRoles(
                LIST_ROLES_CONVERTER.apply(user.getRoles())
        );
        return dto;
    };

    public static final Function<UserDto, User> FROM_USER_DTO = (dto) -> {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setCreated(dto.getCreated());
        user.setUpdated(dto.getUpdated());
        return user;
    };
}
