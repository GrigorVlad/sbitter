package me.grigor.sbitter.services;

import me.grigor.sbitter.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    List<Post> getAllAuthorPosts(Long authorId);

    List<Post> getAllUsersPosts(List<Long> usersIds);

    Post addNewPost(Post newPost);

    void deletePost(Long postId);

}
