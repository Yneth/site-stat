package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.UserDAO;
import ua.social.domain.User;

import javax.persistence.EntityManager;

@Repository
public class UserJpaDAO extends AbstractJpaDAO<User> implements UserDAO {
    public UserJpaDAO() {
        super(User.class);
    }
}
