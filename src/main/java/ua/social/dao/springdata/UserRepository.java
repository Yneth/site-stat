package ua.social.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.social.dao.UserDAO;
import ua.social.domain.User;

import java.util.Optional;

public interface UserRepository extends UserDAO, JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
}
