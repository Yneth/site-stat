package ua.abond.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.abond.social.dao.AuthorityDAO;
import ua.abond.social.domain.Authority;

import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AuthorityJpaDAO extends AbstractJpaDAO<Authority> implements AuthorityDAO {
    public AuthorityJpaDAO() {
        super(Authority.class);
    }

    @Override
    public Optional<Authority> findByName(String name) {
        TypedQuery<Authority> query = entityManager.
                createQuery("select a from Authority a where a.name = :name", Authority.class);
        query.setParameter("name", name);

        return query.getResultList().stream().findFirst();
    }
}
