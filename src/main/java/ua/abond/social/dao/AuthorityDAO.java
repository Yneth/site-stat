package ua.abond.social.dao;

import ua.abond.social.domain.Authority;

import java.util.Optional;

// TODO: add documentation
public interface AuthorityDAO extends DAO<Authority> {
    Optional<Authority> findOneWithName(String name);
}
