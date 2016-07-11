package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.Site;
import ua.abond.social.web.rest.dto.SiteDTO;

import java.util.Optional;

public interface SiteService {
    Site createSite(SiteDTO siteDTO);

    Site updateSite(SiteDTO site);

    void deleteSite(Site network);

    void deleteById(Long id);

    Optional<Site> getByIdWithSessions(Long id);
    Page<Site> getByUserId(Long id, Pageable pageable);
}
