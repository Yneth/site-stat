package ua.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;
import ua.social.domain.User;
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
        return null;
    }

    @Override
    public void updateNetwork(SocialNetwork network) {

    }

    @Override
    public void deleteNetwork(SocialNetwork network) {

    }

    @Override
    @Transactional(readOnly = true)
    @PostAuthorize("hasRole('ROLE_USER') AND hasPermission(returnObject, 'read')")
    public Optional<SocialNetwork> getById(Long id) {
        return socialNetworkDAO.findByIdFullyFetched(id);
    }

    @Override
    public Optional<SocialNetwork> getByUserId(Long id) {
        return null;
    }

    @Override
    public Optional<SocialNetwork> getByUser(User user) {
        return null;
    }
}
