package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.AuthorityDAO;
import ua.social.domain.Authority;

@Repository
public class AuthorityJpaDAO extends AbstractJpaDAO<Authority> implements AuthorityDAO {
    public AuthorityJpaDAO(Class<Authority> clazz) {
        super(Authority.class);
    }
}
