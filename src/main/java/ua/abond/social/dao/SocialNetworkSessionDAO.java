package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.SocialNetworkSession;

public interface SocialNetworkSessionDAO extends DAO<SocialNetworkSession> {
    Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable);
}
