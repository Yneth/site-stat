package ua.abond.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.abond.social.domain.SocialNetwork;
import ua.abond.social.dao.SocialNetworkDAO;

import java.util.Optional;

@Repository
public class SocialNetworkJpaDAO
        extends AbstractJpaDAO<SocialNetwork>
        implements SocialNetworkDAO {

    public SocialNetworkJpaDAO() {
        super(SocialNetwork.class);
    }

    @Override
    public Page<SocialNetwork> findByUserLogin(String login, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<SocialNetwork> findByUserId(Long id, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

//    @Override
//    public Optional<SocialNetwork> getByIdWithSessions(Long id) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public Optional<SocialNetwork> findByIdWithSessions(Long id) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public Optional<SocialNetwork> findByIdFullyFetched(Long id) {
//        throw new UnsupportedOperationException();
//    }
}
