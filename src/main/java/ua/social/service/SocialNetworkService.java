package ua.social.service;

import ua.social.domain.SocialNetwork;
import ua.social.domain.User;

import java.util.Optional;

public interface SocialNetworkService {
    SocialNetwork createNetwork(SocialNetwork network);

    void updateNetwork(SocialNetwork network);

    void deleteNetwork(SocialNetwork network);

    Optional<SocialNetwork> getById(Long id);
    Optional<SocialNetwork> getByUserId(Long id);
    Optional<SocialNetwork> getByUser(User user);
}
