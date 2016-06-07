package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.SocialNetworkSessionDAO;
import ua.social.domain.SocialNetworkSession;

import javax.persistence.EntityManager;

@Repository
public class SocialNetworkSessionJpaDAO
        extends AbstractJpaDAO<SocialNetworkSession>
        implements SocialNetworkSessionDAO {
    public SocialNetworkSessionJpaDAO() {
        super(SocialNetworkSession.class);
    }
}
