package ua.social.dao.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.social.dao.SocialNetworkSessionDAO;
import ua.social.domain.SocialNetworkSession;

public interface SocialNetworkSessionRepository
        extends SocialNetworkSessionDAO, JpaRepository<SocialNetworkSession, Long> {
    @Override
    @Query("select sns from SocialNetworkSession sns where sns.socialNetwork.id = ?1")
    Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable);
}
