package ua.social.dao;

import ua.social.domain.Authority;

import java.util.Optional;

public interface AuthorityDAO extends DAO<Authority> {
    Optional<Authority> findOne(String name);
}
