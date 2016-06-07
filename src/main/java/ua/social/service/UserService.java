package ua.social.service;

import ua.social.domain.User;

import java.util.Optional;

public interface UserService {
    User register();



    Optional<User> findByUsername(String username);
}
