package ua.abond.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.abond.social.config.Constants;
import ua.abond.social.dao.AuthorityDAO;
import ua.abond.social.dao.UserDAO;
import ua.abond.social.domain.Authority;
import ua.abond.social.domain.User;
import ua.abond.social.security.SecurityUtils;
import ua.abond.social.service.UserService;
import ua.abond.social.service.util.RandomUtil;
import ua.abond.social.web.rest.dto.ManagedUserDTO;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityDAO authorityDAO;

    @Override
    public User createUser(ManagedUserDTO user) {
        User newUser = new User();

        newUser.setActivated(true);

        newUser.setEmail(user.getEmail());
        newUser.setLogin(user.getLogin());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        if (user.getLangKey() == null) {
            newUser.setLangKey(Constants.DEFAULT_LANG_KEY);
        } else {
            newUser.setLangKey(user.getLangKey());
        }
        Set<Authority> authorities = new HashSet<>();
        authorityDAO.findByName("ROLE_USER").ifPresent(r -> authorities.add(r));
        newUser.setAuthorities(authorities);
        userDAO.save(newUser);
        log.debug("Created information for User {}", newUser);
        return newUser;
    }

    @Override
    public User createUserInformation(String login, String password, String firstName, String lastName, String email,
                                      String langKey) {
        User newUser = new User();
        Set<Authority> authorities = new HashSet<>();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        newUser.setPassword(encodedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLastName(langKey);

        newUser.setActivated(false);
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorityDAO.findByName("ROLE_USER").ifPresent(authority -> authorities.add(authority));
        newUser.setAuthorities(authorities);
        userDAO.save(newUser);
        log.debug("Created information for User {}", newUser);
        return newUser;
    }

    @Override
    public Optional<User> activateRegistration(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUserInformation(String firstName, String lastName, String email) {
        userDAO.getById(SecurityUtils.getCurrentUserId()).ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            log.debug("Updated information for User {}", u);
            userDAO.save(u);
        });
    }

    @Override
    public void deleteUserInformation(String login) {
        userDAO.findOneByLogin(login).ifPresent(u -> {
            userDAO.delete(u);
            log.debug("Deleted information for User {}", u);
        });
    }

    @Override
    public void changePassword(String password) {
        userDAO.getById(SecurityUtils.getCurrentUserId()).ifPresent(u -> {
            u.setPassword(passwordEncoder.encode(password));
            userDAO.save(u);
            log.debug("Changed password for User: {}", u);
        });
    }

    @Override
    public Optional<User> getCurrentUser() {
        if (SecurityUtils.getCurrentUserId() != null) {
            getUserByIdWithAuthorities(SecurityUtils.getCurrentUserId());
        }
        return getUserByLoginWithAuthorities(SecurityUtils.getCurrentUserLogin());
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userDAO.findOneByLogin(login);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDAO.findOneByEmail(email);
    }

    @Override
    public Optional<User> getUserByLoginWithAuthorities(String login) {
        return userDAO.findOneByLoginWithAuthorities(login);
    }

    @Override
    public Optional<User> getUserByIdWithAuthorities(Long id) {
        return userDAO.findOneByIdWithAuthorities(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDAO.getById(id);
    }
}
