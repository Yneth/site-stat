package ua.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import ua.social.domain.SocialNetwork;
import ua.social.domain.User;

import java.util.Optional;

public interface SocialNetworkService {
    SocialNetwork createNetwork(SocialNetwork network);

    void updateNetwork(SocialNetwork network);

    void deleteNetwork(SocialNetwork network);

    Optional<SocialNetwork> getById(Long id);
    Page<SocialNetwork> getByUserId(Long id, Pageable pageable);
}
