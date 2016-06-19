package ua.social.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.social.dao.AuthorityDAO;
import ua.social.domain.Authority;

import java.util.Optional;

public interface AuthorityRepository extends AuthorityDAO, JpaRepository<Authority, Long> {
    @Query("select a from Authority a where a.name = :name")
    Optional<Authority> findOneWithName(String name);
}
