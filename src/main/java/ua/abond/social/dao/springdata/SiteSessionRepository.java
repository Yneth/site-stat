package ua.abond.social.dao.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.SiteSession;

public interface SiteSessionRepository
        extends SiteSessionDAO, JpaRepository<SiteSession, Long> {
    @Override
    @Query("select sns from SiteSession sns where sns.socialNetwork.id = ?1")
    Page<SiteSession> getBySocialNetworkId(Long id, Pageable pageable);
}
