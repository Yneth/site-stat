package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkDAO extends DAO<SocialNetwork> {
    Page<SocialNetwork> findByUserLogin(String login, Pageable pageable);
    Page<SocialNetwork> findByUserId(Long id, Pageable pageable);
    Optional<SocialNetwork> findByIdWithSessions(Long id);
    Optional<SocialNetwork> findByIdFullyFetched(Long id);
}
