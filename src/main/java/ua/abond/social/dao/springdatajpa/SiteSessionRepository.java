package ua.abond.social.dao.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.SiteSession;

import java.time.LocalDateTime;

public interface SiteSessionRepository
        extends SiteSessionDAO, JpaRepository<SiteSession, Long> {
    @Override
    @Query("select new SiteSession(ss.id, ss.start, ss.end, ss. duration) from SiteSession ss where ss.site.id = ?1")
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);

    @Override
    @Query("select new SiteSession(ss.id, ss.start, ss.end, ss.duration) from SiteSession ss " +
            "where " +
            "ss.site.id = ?1 " +
            "and " +
            "ss.start >= ?2 " +
            "and " +
            "ss.start < ?3")
    Page<SiteSession> getBySiteIdBetweenDates(Long siteId, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
