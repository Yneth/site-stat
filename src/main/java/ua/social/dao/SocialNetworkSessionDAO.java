package ua.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ua.social.domain.SocialNetworkSession;

import java.util.Optional;

public interface SocialNetworkSessionDAO extends DAO<SocialNetworkSession> {
    Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable);
}
