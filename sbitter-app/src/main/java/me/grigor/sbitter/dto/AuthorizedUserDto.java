package me.grigor.sbitter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedUserDto {

    private Long id;
    private String username;
    private String token;
    private List<String> roles;

}
