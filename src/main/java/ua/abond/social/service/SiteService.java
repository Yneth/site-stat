package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.Site;

import java.util.Optional;

public interface SiteService {
    Site createSite(Site network);

    void updateSite(Site network);

    void deleteSite(Site network);

    Optional<Site> getByIdWithSessions(Long id);
    Page<Site> getByUserId(Long id, Pageable pageable);
}
