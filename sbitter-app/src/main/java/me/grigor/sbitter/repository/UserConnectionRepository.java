package me.grigor.sbitter.repository;

import me.grigor.sbitter.entity.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {

    List<UserConnection> findAllByUserIdOrderByCreatedDesc(Long userId);

    List<UserConnection> findAllByFollowerIdOrderByCreatedDesc(Long followerId);

    Boolean existsByUserIdAndFollowerId(Long userId, Long followerId);

}
