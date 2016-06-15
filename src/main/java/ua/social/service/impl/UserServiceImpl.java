package ua.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.social.dao.AuthorityDAO;
import ua.social.domain.User;
import ua.social.service.UserService;
import ua.social.web.rest.dto.ManagedUserDTO;
import ua.social.dao.UserDAO;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityDAO authorityDAO;

    @Override
    public Optional<User> createUserInformation(ManagedUserDTO user) {
        return null;
    }

    @Override
    public Optional<User> activateRegistration(String key) {
        return null;
    }

    @Override
    public Optional<User> updateUserInformation(String firstName, String secondName, String email, String langKey) {
        return null;
    }

    @Override
    public Optional<User> deleteUserInformation(String login) {
        return null;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return null;
    }
}
