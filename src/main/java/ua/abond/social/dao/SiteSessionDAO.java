package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;

import java.util.List;

public interface SiteSessionDAO extends DAO<SiteSession> {
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);
//    Page<SiteSession> getBySiteIdWithSpec(Long siteId, Specification<SiteSession> spec, Pageable pageable);
    List<SiteSession> findAll(Specification<SiteSession> spec);
}
