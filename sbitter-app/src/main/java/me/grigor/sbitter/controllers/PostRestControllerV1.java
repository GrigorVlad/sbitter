package me.grigor.sbitter.controllers;

import me.grigor.sbitter.dto.PostDto;
import me.grigor.sbitter.dto.UserDto;
import me.grigor.sbitter.dto.converters.PostConverter;
import me.grigor.sbitter.dto.converters.UserConverter;
import me.grigor.sbitter.entity.Post;
import me.grigor.sbitter.services.PostService;
import me.grigor.sbitter.services.UserConnectionService;
import me.grigor.sbitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostRestControllerV1 {

    private final PostService postService;
    private final UserService userService;
    private final UserConnectionService userConnectionService;

    @Autowired
    public PostRestControllerV1(PostService postService,
                                UserService userService,
                                UserConnectionService userConnectionService) {
        this.postService = postService;
        this.userService = userService;
        this.userConnectionService = userConnectionService;
    }

    @RequestMapping(value = "/{userId}/own", method = RequestMethod.GET)
    @ResponseBody
    public List<PostDto> getAllUserIdPosts(@PathVariable Long userId) {
        return postService.getAllAuthorPosts(userId).stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{userId}/followings", method = RequestMethod.GET)
    @ResponseBody
    public List<PostDto> getAllUserIdFollowingsPosts(@PathVariable Long userId) {
        List<Long> userFollowing = userConnectionService.getUserFollowing(userId);
        userFollowing.add(userId);
        return postService.getAllUsersPosts(userFollowing).stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{userId}/publish", method = RequestMethod.POST)
    @ResponseBody
    public PostDto publishNewPost(@PathVariable Long userId, @RequestBody PostDto postDto) {
        Post newPost = new Post();
        newPost.setText(postDto.getText());
        newPost.setAuthorId(userId);
        Post post = postService.addNewPost(newPost);
        return convertToPostDto(post);
    }

    private PostDto convertToPostDto(Post post) {
        UserDto author = UserConverter.TO_USER_DTO.apply(
                userService.findById(post.getAuthorId())
        );
        PostDto postDto = PostConverter.TO_POST_DTO.apply(post);
        postDto.setAuthor(author);

        return postDto;
    }
}
