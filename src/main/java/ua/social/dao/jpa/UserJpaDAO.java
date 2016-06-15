package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.UserDAO;
import ua.social.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        User user = (User) query.getSingleResult();
        return Optional.ofNullable(user);
    }
}
