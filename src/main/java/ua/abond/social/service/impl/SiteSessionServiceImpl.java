package ua.abond.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.service.SiteSessionService;

import java.util.Optional;

@Service
public class SiteSessionServiceImpl implements SiteSessionService {
    private final Logger log = LoggerFactory.getLogger(SiteSessionServiceImpl.class);

    @Autowired
    private SiteSessionDAO siteSessionDAO;

    @Override
    public SiteSession createSite(SiteSession session) {
        log.debug("Request to save SiteSession {}", session);
        return siteSessionDAO.save(session);
    }

    @Override
    public void updateSite(SiteSession session) {
        log.debug("Request to update SiteSession {}", session);
        siteSessionDAO.save(session);
    }

    @Override
    public void deleteSite(SiteSession session) {
        log.debug("Request to delete SiteSession {}", session);
        siteSessionDAO.delete(session);
    }

    @Override
    public Optional<SiteSession> getById(Long id) {
        log.debug("Request to get SiteSession {}", id);
        return siteSessionDAO.getById(id);
    }

    @Override
    public Page<SiteSession> getBySiteId(Long id, Pageable pageable) {
        log.debug("Request to get pageable SiteSession {}", id);
        return siteSessionDAO.getBySiteId(id, pageable);
    }
}
