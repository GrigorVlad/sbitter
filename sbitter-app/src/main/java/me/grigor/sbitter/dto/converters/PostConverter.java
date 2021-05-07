package me.grigor.sbitter.dto.converters;

import me.grigor.sbitter.dto.PostDto;
import me.grigor.sbitter.dto.UserDto;
import me.grigor.sbitter.entity.Post;

import java.util.function.Function;

public class PostConverter {

    public static Function<Post, PostDto> TO_POST_DTO = (post) -> {
        //    private Long id;
        //    private UserDto author;
        //    private String text;
        //    private Date created;

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setText(post.getText());
        dto.setCreated(post.getCreated());

        UserDto author = new UserDto();
        author.setId(post.getAuthorId());
        dto.setAuthor(author);

        return dto;
    };

    public static Function<PostDto, Post> FROM_POST_DTO = (dto) -> {
        Post post = new Post();
        post.setId(dto.getId());
        post.setAuthorId(dto.getAuthor().getId());
        post.setText(dto.getText());
        post.setCreated(dto.getCreated());

        return post;
    };
}
