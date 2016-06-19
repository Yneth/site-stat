package ua.social.dao;

import ua.social.dao.DAO;
import ua.social.domain.User;

import java.util.Optional;

public interface UserDAO extends DAO<User> {
    Optional<User> findOneByLogin(String login);
}
