package me.grigor.sbitter.repository;

import me.grigor.sbitter.entity.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {

    List<UserConnection> findAllByUserIdOrderByCreated(Long userId);

    List<UserConnection> findAllByFollowerIdOrderByCreated(Long followerId);

    Boolean existsByUserIdAndFollowerId(Long userId, Long followerId);

}
