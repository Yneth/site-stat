package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkService {
    SocialNetwork createNetwork(SocialNetwork network);

    void updateNetwork(SocialNetwork network);

    void deleteNetwork(SocialNetwork network);

    Optional<SocialNetwork> getById(Long id);
    Page<SocialNetwork> getByUserId(Long id, Pageable pageable);
}
