package me.grigor.sbitter.services;

import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.entity.UserConnection;

import java.util.List;

public interface UserConnectionService {

    List<Long> getUserFollowers(Long userId);

    List<Long> getUserFollowing(Long userId);

    UserConnection addConnection(UserConnection userConnection);

    boolean checkFollower(Long userId, Long personId);

    boolean checkFollowing(Long userId, Long personId);

}
