package me.grigor.sbitter.repository;

import me.grigor.sbitter.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthorId(Long authorId);

    List<Post> findAllByAuthorIdOrderByCreatedDesc(Long authorId);

}
