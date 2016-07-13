package ua.abond.social.dao.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.abond.social.dao.AuthorityDAO;
import ua.abond.social.domain.Authority;

import java.util.Optional;

public interface AuthorityRepository extends AuthorityDAO, JpaRepository<Authority, Long> {
    @Override
    @Query("select a from Authority a where a.name = ?1")
    Optional<Authority> findOneWithName(String name);
}
