package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.AuthorityDAO;
import ua.social.domain.Authority;

import javax.persistence.Query;
import java.util.Optional;

@Repository
public class AuthorityJpaDAO extends AbstractJpaDAO<Authority> implements AuthorityDAO {
    public AuthorityJpaDAO() {
        super(Authority.class);
    }

    @Override
    public Optional<Authority> findOne(String name) {
        Query query = entityManager.createQuery("select a from Authority a where a.name = :name");
        query.setParameter("name", name);

        return query.getResultList().stream().findFirst();
    }
}
