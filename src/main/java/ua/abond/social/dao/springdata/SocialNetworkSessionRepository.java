package ua.abond.social.dao.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SocialNetworkSessionDAO;
import ua.abond.social.domain.SocialNetworkSession;

public interface SocialNetworkSessionRepository
        extends SocialNetworkSessionDAO, JpaRepository<SocialNetworkSession, Long> {
    @Override
    @Query("select sns from SocialNetworkSession sns where sns.socialNetwork.id = ?1")
    Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable);
}
