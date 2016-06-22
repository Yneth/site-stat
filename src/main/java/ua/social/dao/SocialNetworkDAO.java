package ua.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;
import ua.social.dao.DAO;
import ua.social.domain.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkDAO extends DAO<SocialNetwork> {
    Page<SocialNetwork> findByUserLogin(String login, Pageable pageable);
    Page<SocialNetwork> findByUserId(Long id, Pageable pageable);
    Optional<SocialNetwork> findByIdWithSessions(Long id);
    Optional<SocialNetwork> findByIdFullyFetched(Long id);
}
