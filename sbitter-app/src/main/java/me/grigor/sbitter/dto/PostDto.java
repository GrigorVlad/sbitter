package me.grigor.sbitter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long id;
    private UserDto author;
    private String text;
    private Date created;

}
