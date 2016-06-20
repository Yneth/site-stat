package ua.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.social.dao.SocialNetworkSessionDAO;
import ua.social.domain.SocialNetworkSession;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class SocialNetworkSessionJpaDAO
        extends AbstractJpaDAO<SocialNetworkSession>
        implements SocialNetworkSessionDAO {
    public SocialNetworkSessionJpaDAO() {
        super(SocialNetworkSession.class);
    }

    @Override
    public Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
