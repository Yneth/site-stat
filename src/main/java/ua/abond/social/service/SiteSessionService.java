package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.SiteSession;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public interface SiteSessionService {
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    SiteSession createSite(SiteSession session);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    void updateSite(SiteSession session);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    void deleteSite(SiteSession session);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    Optional<SiteSession> getById(Long id);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    Page<SiteSession> getBySiteId(Long id, Pageable pageable);

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly =  true)
    Page<SiteSession> getBySiteIdBetweenDates(Long id, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
