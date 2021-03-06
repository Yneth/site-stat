package ua.abond.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.service.SiteSessionService;
import ua.abond.social.web.rest.dto.SiteSessionDTO;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SiteSessionServiceImpl implements SiteSessionService {
    private final Logger log = LoggerFactory.getLogger(SiteSessionServiceImpl.class);

    @Autowired
    private SiteSessionDAO siteSessionDAO;

    @Override
    public SiteSession createSession(SiteSession session) {
        log.debug("Request to save SiteSession {}", session);
        return siteSessionDAO.save(session);
    }

    @Override
    public SiteSessionDTO createSession(SiteSessionDTO session) {
        log.debug("Request to save SiteSession {}", session);
        SiteSession res = new SiteSession();
        res.setSite(new Site(session.getSiteId()));
        res.setStartDateTime(session.getStartTime());
        res.setEndDateTime(session.getEndTime());
        res.updateDuration();
        siteSessionDAO.save(res);
        session.setId(res.getId());
        return session;
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
    public void deleteById(Long id) {
        log.debug("Request to delete SiteSession with id Long {}", id);
        siteSessionDAO.deleteById(id);
    }

    @Override
    public Optional<SiteSession> getById(Long id) {
        log.debug("Request to get SiteSession {}", id);
        return siteSessionDAO.getById(id);
    }

    @Override
    public Page<SiteSession> getBySiteId(Long id, Pageable pageable) {
        log.debug("Request to get pageable SiteSessions for Site with id Long {}", id);
        return siteSessionDAO.getBySiteId(id, pageable);
    }

    @Override
    public Page<SiteSession> getBySiteIdBetweenDates(Long id,
                                                     LocalDateTime start,
                                                     LocalDateTime end,
                                                     Pageable pageable) {
        log.debug("Request to get pageable SiteSessions between dates for Site with id Long {}", id);
        return siteSessionDAO.getBySiteIdBetweenDates(id, start, end, pageable);
    }
}
