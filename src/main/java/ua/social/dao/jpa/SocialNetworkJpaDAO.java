package ua.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;

import java.util.Optional;

@Repository
public class SocialNetworkJpaDAO
        extends AbstractJpaDAO<SocialNetwork>
        implements SocialNetworkDAO {

    public SocialNetworkJpaDAO() {
        super(SocialNetwork.class);
    }

    @Override
    public Page<SocialNetwork> findForUserLogin(String login, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<SocialNetwork> findForUserId(Long id, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<SocialNetwork> findByIdWithSessions(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<SocialNetwork> findByIdFullyFetched(Long id) {
        throw new UnsupportedOperationException();
    }
}
