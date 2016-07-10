package ua.abond.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.Site;
import ua.abond.social.service.SiteService;
import ua.abond.social.dao.SiteDAO;

import java.util.Optional;

@Service
@Transactional
public class SiteServiceImpl implements SiteService {
    private final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private SiteDAO siteDAO;

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Override
    public Site createSite(Site network) {
        log.debug("Request to save Site {}", network);
        return siteDAO.save(network);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Override
    public void updateSite(Site network) {
        log.debug("Request to update Site {}", network);
        siteDAO.save(network);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Override
    public void deleteSite(Site network) {
        log.debug("Request to delete Site {}", network);
        siteDAO.delete(network);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    @Override
    public Optional<Site> getByIdWithSessions(Long id) {
        log.debug("Request to read social network", id);
        Optional<Site> byId = siteDAO.getById(id).map(sn -> {
            sn.getSiteSessions().size();
            return sn;
        });
        return byId;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @Override
    @Transactional(readOnly = true)
    public Page<Site> getByUserId(Long id, Pageable pageable) {
        log.debug("Request to read all Site {} for requested User id", id);
        return siteDAO.findByUserId(id, pageable);
    }
}
