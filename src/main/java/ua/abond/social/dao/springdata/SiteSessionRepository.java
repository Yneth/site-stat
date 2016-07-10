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
    @Query("select ss from SiteSession ss where ss.site.id = ?1")
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);
}
