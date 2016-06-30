package ua.abond.social.dao;

import ua.abond.social.domain.User;

import java.util.Optional;

public interface UserDAO extends DAO<User> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByLoginWithAuthorities(String login);
    Optional<User> findOneByEmail(String email);
}
