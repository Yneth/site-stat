package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.UserDAO;
import ua.social.domain.User;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import javax.persistence.Subgraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserJpaDAO extends AbstractJpaDAO<User> implements UserDAO {
    public UserJpaDAO() {
        super(User.class);
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        Query query = entityManager.createQuery("select u from User u where u.login = :login");
        query.setParameter("login", login);

        // link that describes choice of getResultList over getSingleResult
        // http://sysout.be/2011/03/09/why-you-should-never-use-getsingleresult-in-jpa/
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<User> findOneByLoginWithAuthorities(String login) {
        Query query = entityManager.createQuery("select u from User u join fetch u.authorities where u.login = :login");
        query.setParameter("login", login);

        // link that describes choice of getResultList over getSingleResult
        // http://sysout.be/2011/03/09/why-you-should-never-use-getsingleresult-in-jpa/
        return query.getResultList().stream().findFirst();
    }
}
