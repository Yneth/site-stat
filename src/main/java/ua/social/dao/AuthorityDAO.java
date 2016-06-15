package ua.social.dao;

import ua.social.domain.Authority;

public interface AuthorityDAO extends DAO<Authority> {
    Authority findOne(String name);
}
