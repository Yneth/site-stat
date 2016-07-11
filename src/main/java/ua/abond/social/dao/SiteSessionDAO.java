package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;

import java.time.ZonedDateTime;
import java.util.List;

public interface SiteSessionDAO extends DAO<SiteSession> {
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);

    // TODO: slow
    Page<SiteSession> findAll(Specification<SiteSession> spec, Pageable pageable);

    Page<SiteSession> getBySiteIdBetweenDates(Long siteId, ZonedDateTime start, ZonedDateTime end, Pageable pageable);
}
