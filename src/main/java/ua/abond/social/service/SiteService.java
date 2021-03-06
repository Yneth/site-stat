package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.Site;
import ua.abond.social.web.rest.dto.SiteSummary;
import ua.abond.social.web.rest.dto.SiteDTO;

import java.util.Optional;


// TODO: make it accessible only to user
@Transactional
public interface SiteService {
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    Site createSite(SiteDTO siteDTO);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    Site updateSite(SiteDTO site);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    void deleteSite(Site Site);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    Optional<Site> getById(Long id);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    Page<Site> getByUserId(Long id, Pageable pageable);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    Page<SiteSummary> getStatisticsByUserId(Long userId, Pageable pageable);
}
