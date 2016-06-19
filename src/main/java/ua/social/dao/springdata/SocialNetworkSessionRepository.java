package ua.social.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.social.dao.SocialNetworkSessionDAO;
import ua.social.domain.SocialNetworkSession;

public interface SocialNetworkSessionRepository
        extends SocialNetworkSessionDAO, JpaRepository<SocialNetworkSession, Long> {
}
