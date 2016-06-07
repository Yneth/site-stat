package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;

import javax.persistence.NamedQuery;

@Repository
public class SocialNetworkJpaDAO
        extends AbstractJpaDAO<SocialNetwork>
        implements SocialNetworkDAO {

    public SocialNetworkJpaDAO() {
        super(SocialNetwork.class);
    }
}
