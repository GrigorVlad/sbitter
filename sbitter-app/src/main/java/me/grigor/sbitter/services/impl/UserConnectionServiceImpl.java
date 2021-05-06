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
    public List<User> getUserFollowers(Long userId) {
        List<UserConnection> userConnections =
                userConnectionRepository.findAllByUserIdOrderByCreated(userId);
        log.info("[UserConnectionServiceImpl, method: getUserFollowers] " +
                        "Found {} followers users of user with id: {}",
                userConnections.size(), userId);

        List<User> followers = prepareUsers(userConnections, UserConnection::getFollowerId);
        log.info("[UserConnectionServiceImpl, method: getUserFollowers] " +
                        "Found {} existing followers of user with id: {}",
                followers.size(), userId);
        return followers;
    }

    @Override
    public List<User> getUserFollowing(Long userId) {
        List<UserConnection> userConnections =
                userConnectionRepository.findAllByFollowerIdOrderByCreated(userId);
        log.info("[UserConnectionServiceImpl, method: getUserFollowing] " +
                        "Found {} following users of user with id: {}",
                userConnections.size(), userId);

        List<User> following = prepareUsers(userConnections, UserConnection::getUserId);
        log.info("[UserConnectionServiceImpl, method: getUserFollowers] " +
                        "Found {} existing following users of user with id: {}",
                following.size(), userId);
        return following;
    }

    private List<User> prepareUsers(List<UserConnection> userConnections,
                                    Function<UserConnection, Long> getUserId) {
        List<User> result = new ArrayList<>();
        for (UserConnection userConnection : userConnections) {
            Long userId = getUserId.apply(userConnection);
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                log.warn("[UserConnectionServiceImpl, method: prepareUsers] " +
                        "User with id {}, not found", userId);
                continue;
            }
            result.add(user);
        }
        return result;
    }

    @Override
    public UserConnection addConnection(UserConnection userConnection) {
        UserConnection newUserConnection = userConnectionRepository.save(userConnection);
        log.info("[UserConnectionServiceImpl, method: prepareUsers] " +
                "Connection {} successfully created", newUserConnection);
        return newUserConnection;
    }
}