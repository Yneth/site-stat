package ua.abond.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.dao.SocialNetworkSessionDAO;
import ua.abond.social.domain.SocialNetworkSession;
import ua.abond.social.service.SocialNetworkSessionService;

import java.util.Optional;

@Service
@Transactional
public class SocialNetworkSessionServiceImpl implements SocialNetworkSessionService {
    private final Logger log = LoggerFactory.getLogger(SocialNetworkSessionServiceImpl.class);

    @Autowired
    private SocialNetworkSessionDAO socialNetworkSessionDAO;

    @Override
    public SocialNetworkSession createSession(SocialNetworkSession session) {
        log.debug("Request to save SocialNetworkSession {}", session);
        return socialNetworkSessionDAO.save(session);
    }

    @Override
    public void updateSession(SocialNetworkSession session) {
        log.debug("Request to update SocialNetworkSession {}", session);
        socialNetworkSessionDAO.save(session);
    }

    @Override
    public void deleteSession(SocialNetworkSession session) {
        log.debug("Request to delete SocialNetworkSession {}", session);
        socialNetworkSessionDAO.delete(session);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SocialNetworkSession> getById(Long id) {
        log.debug("Request to get SocialNetworkSession {}", id);
        return socialNetworkSessionDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable) {
        log.debug("Request to get pageable SocialNetworkSession {}", id);
        return socialNetworkSessionDAO.getBySocialNetworkId(id, pageable);
    }
}
