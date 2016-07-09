package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.SiteSession;

import java.util.Optional;

public interface SiteSessionService {
    SiteSession createSite(SiteSession session);

    void updateSite(SiteSession session);

    void deleteSite(SiteSession session);

    Optional<SiteSession> getById(Long id);

    Page<SiteSession> getBySiteId(Long id, Pageable pageable);
}
