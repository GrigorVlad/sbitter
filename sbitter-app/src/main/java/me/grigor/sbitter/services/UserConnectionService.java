package me.grigor.sbitter.services;

import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.entity.UserConnection;

import java.util.List;

public interface UserConnectionService {

    List<User> getUserFollowers(Long userId);

    List<User> getUserFollowing(Long userId);

    UserConnection addConnection(UserConnection userConnection);

}
