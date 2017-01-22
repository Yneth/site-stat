package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ua.abond.social.domain.SiteSession;

import java.time.LocalDateTime;

public interface SiteSessionDAO extends DAO<SiteSession> {
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);

    // TODO: slow
    Page<SiteSession> findAll(Specification<SiteSession> spec, Pageable pageable);

    Page<SiteSession> getBySiteIdBetweenDates(Long siteId, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
