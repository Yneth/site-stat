package ua.abond.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.abond.social.dao.UserDAO;
import ua.abond.social.domain.User;

import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class UserJpaDAO extends AbstractJpaDAO<User> implements UserDAO {
    public UserJpaDAO() {
        super(User.class);
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        TypedQuery<User> query = entityManager.
                createQuery("select u from User u where u.login = :login", User.class);
        query.setParameter("login", login);

        // link that describes choice of getResultList over getSingleResult
        // http://sysout.be/2011/03/09/why-you-should-never-use-getsingleresult-in-jpa/
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<User> findOneByIdWithAuthorities(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findOneByLoginWithAuthorities(String login) {
        TypedQuery<User> query = entityManager.
                createQuery("select u from User u join fetch u.authorities where u.login = :login", User.class);
        query.setParameter("login", login);

        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        throw new UnsupportedOperationException();
    }
}
