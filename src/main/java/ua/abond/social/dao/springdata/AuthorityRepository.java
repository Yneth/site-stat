package ua.abond.social.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.AuthorityDAO;
import ua.abond.social.domain.Authority;

import java.util.Optional;

public interface AuthorityRepository extends AuthorityDAO, JpaRepository<Authority, Long> {
    @Override
    @Query("select a from Authority a where a.name = :name")
    Optional<Authority> findOneWithName(String name);
}
