package ua.abond.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.abond.social.dao.SocialNetworkSessionDAO;
import ua.abond.social.domain.SocialNetworkSession;

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
