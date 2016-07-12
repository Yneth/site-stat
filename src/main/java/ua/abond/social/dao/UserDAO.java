package ua.abond.social.dao;

import ua.abond.social.domain.User;

import java.util.Optional;

// TODO: add documentation
public interface UserDAO extends DAO<User> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByIdWithAuthorities(Long id);
    Optional<User> findOneByLoginWithAuthorities(String login);
    Optional<User> findOneByEmail(String email);
}
