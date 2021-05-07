package me.grigor.sbitter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

}
