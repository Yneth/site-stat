package ua.social.service.impl;

import org.springframework.stereotype.Component;
import ua.social.domain.User;
import ua.social.service.UserService;

import java.util.Optional;

@Component("userService")
public class UserServiceImpl implements UserService {
    @Override
    public User register() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return null;
    }
}
