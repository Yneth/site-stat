package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.SiteSession;

public interface SiteSessionDAO extends DAO<SiteSession> {
    Page<SiteSession> getBySocialNetworkId(Long id, Pageable pageable);
}
