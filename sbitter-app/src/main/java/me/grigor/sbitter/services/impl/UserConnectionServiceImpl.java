package me.grigor.sbitter.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.grigor.sbitter.entity.User;
import me.grigor.sbitter.entity.UserConnection;
import me.grigor.sbitter.repository.UserConnectionRepository;
import me.grigor.sbitter.repository.UserRepository;
import me.grigor.sbitter.services.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserConnectionServiceImpl implements UserConnectionService {

    private final UserRepository userRepository;

    private final UserConnectionRepository userConnectionRepository;

    @Autowired
    public UserConnectionServiceImpl(UserRepository userRepository,
                                     UserConnectionRepository userConnectionRepository) {
        this.userRepository = userRepository;
        this.userConnectionRepository = userConnectionRepository;
    }

    @Override
    public List<Long> getUserFollowers(Long userId) {
        List<UserConnection> userConnections =
                userConnectionRepository.findAllByUserIdOrderByCreatedDesc(userId);
        log.info("[UserConnectionServiceImpl.getUserFollowers] " +
                        "Found {} followers of user with id: {}",
                userConnections.size(), userId);
        return userConnections.stream()
                .map(UserConnection::getFollowerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getUserFollowing(Long userId) {
        List<UserConnection> userConnections =
                userConnectionRepository.findAllByFollowerIdOrderByCreatedDesc(userId);
        log.info("[UserConnectionServiceImpl.getUserFollowing] " +
                        "Found {} following users of user with id: {}",
                userConnections.size(), userId);

        return userConnections.stream()
                .map(UserConnection::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public UserConnection addConnection(UserConnection userConnection) {
        UserConnection newUserConnection = userConnectionRepository.save(userConnection);
        log.info("[UserConnectionServiceImpl.prepareUsers] " +
                "Connection {} successfully created", newUserConnection);
        return newUserConnection;
    }

    @Override
    public boolean checkFollower(Long userId, Long personId) {
        Boolean checkFollowers = userConnectionRepository.existsByUserIdAndFollowerId(userId, personId);
        log.info("[UserConnectionServiceImpl.checkFollower] " +
                "Person {} follower by user {} is {}", personId, userId, checkFollowers);
        return checkFollowers;
    }

    @Override
    public boolean checkFollowing(Long userId, Long personId) {
        Boolean checkFollowing = userConnectionRepository.existsByUserIdAndFollowerId(personId, userId);
        log.info("[UserConnectionServiceImpl.checkFollower] " +
                "User {} follower by person {} is {}", userId, personId, checkFollowing);
        return checkFollowing;
    }
}
