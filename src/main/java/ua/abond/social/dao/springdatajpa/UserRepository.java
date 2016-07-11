package ua.abond.social.dao.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.domain.User;
import ua.abond.social.dao.UserDAO;

import java.util.Optional;

public interface UserRepository extends UserDAO, JpaRepository<User, Long> {
    @Override
    @Query("select u from User u join fetch u.authorities where u.login IN ?1")
    Optional<User> findOneByLoginWithAuthorities(String login);

    @Override
    @Query("select u from User u where u.email = ?1")
    Optional<User> findOneByEmail(String email);

    @Override
    void deleteById(Long id);
}
