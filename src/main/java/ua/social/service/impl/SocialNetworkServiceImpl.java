package ua.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;
import ua.social.service.SocialNetworkService;

import java.util.Optional;

@Service
@Transactional
public class SocialNetworkServiceImpl implements SocialNetworkService {
    private final Logger log = LoggerFactory.getLogger(SocialNetworkServiceImpl.class);

    @Autowired
    private SocialNetworkDAO socialNetworkDAO;

    @Override
    public SocialNetwork createNetwork(SocialNetwork network) {
        log.debug("Request to save SocialNetwork {}", network);
        return socialNetworkDAO.save(network);
    }

    @Override
    public void updateNetwork(SocialNetwork network) {
        log.debug("Request to update SocialNetwork {}", network);
        socialNetworkDAO.save(network);
    }

    @Override
    public void deleteNetwork(SocialNetwork network) {
        log.debug("Request to delete SocialNetwork {}", network);
        socialNetworkDAO.delete(network);
    }

    @PostAuthorize("hasRole('ROLE_USER') AND hasPermission(returnObject, 'read')")
    @Transactional(readOnly = true)
    @Override
    public Optional<SocialNetwork> getById(Long id) {
        log.debug("Request to read all SocialNetwork {} for requested id", id);
        return socialNetworkDAO.findByIdFullyFetched(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SocialNetwork> getByUserId(Long id, Pageable pageable) {
        log.debug("Request to read all SocialNetwork {} for requested User id", id);
        return socialNetworkDAO.findByUserId(id, pageable);
    }
}
