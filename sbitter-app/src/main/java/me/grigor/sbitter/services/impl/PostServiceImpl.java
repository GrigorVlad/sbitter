package me.grigor.sbitter.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.grigor.sbitter.entity.BaseEntity;
import me.grigor.sbitter.entity.Post;
import me.grigor.sbitter.repository.PostRepository;
import me.grigor.sbitter.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> result = postRepository.findAll();
        log.info("[PostServiceImpl, method getAllPosts] {} posts was found", result.size());
        return result;
    }

    @Override
    public List<Post> getAllAuthorPosts(Long authorId) {
        List<Post> result = postRepository.findAllByAuthorIdOrderByCreated(authorId);
        log.info("[PostServiceImpl, method getAllAuthorPosts] {} posts authored by {} was found",
                result.size(), authorId);
        return result;
    }

    @Override
    public List<Post> getAllUsersPosts(List<Long> usersIds) {
        List<Post> result = new ArrayList<>();
        usersIds.forEach(userId -> result.addAll(postRepository.findAllByAuthorId(userId)));
        result.sort(Comparator.comparing(BaseEntity::getCreated));
        log.info("[PostServiceImpl, method getAllUsersPosts] {} posts authored by {} was found",
                result.size(), usersIds);
        return result;
    }

    @Override
    public Post addNewPost(Post newPost) {
        Post addedPost = postRepository.save(newPost);
        log.info("[PostServiceImpl, method addNewPost] Successfully add new post: {}", addedPost);
        return addedPost;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
        log.info("[PostServiceImpl, method deletePost] Post with id: {} was successfully deleted", postId);
    }
}
