package ua.abond.social.dao.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.SiteSession;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface SiteSessionRepository
        extends SiteSessionDAO, JpaRepository<SiteSession, Long> {
    @Override
    @Query("select ss from SiteSession ss where ss.site.id = ?1")
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);

    @Override
    @Query("select ss.id, ss.start, ss.end, ss.duration from SiteSession ss " +
            "where " +
            "ss.id = ?1 " +
            "and " +
            "ss.start >= ?2 " +
            "and " +
            "ss.end < ?3")
    Page<SiteSession> getBySiteIdBetweenDates(Long siteId, ZonedDateTime start, ZonedDateTime end, Pageable pageable);
}
