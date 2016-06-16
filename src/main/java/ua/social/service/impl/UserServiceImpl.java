package ua.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.social.config.Constants;
import ua.social.dao.AuthorityDAO;
import ua.social.dao.UserDAO;
import ua.social.domain.Authority;
import ua.social.domain.User;
import ua.social.security.SecurityUtils;
import ua.social.service.UserService;
import ua.social.service.util.RandomUtil;
import ua.social.web.rest.dto.ManagedUserDTO;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        if (user.getAuthorities() != null) {
            Set<Authority> authorities = new HashSet<>();
            user.getAuthorities().stream().forEach(
                    authority -> authorityDAO.findOne(authority).ifPresent(
                            authority1 -> authorities.add(authority1)
                    )
            );
            newUser.setAuthorities(authorities);
        }
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
        authorityDAO.findOne("ROLE_USER").ifPresent(authority -> authorities.add(authority));
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
    public void updateUserInformation(String firstName, String lastName, String email, String langKey) {
        userDAO.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            u.setLangKey(langKey);
            log.debug("Updated information for User {}", u);
            userDAO.update(u);
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
    @Transactional(readOnly = true)
    public Optional<User> getCurrentUser() {
        return getUserByLoginWithAuthorities(SecurityUtils.getCurrentUserLogin());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByLogin(String login) {
        return userDAO.findOneByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByLoginWithAuthorities(String login) {
        return userDAO.findOneByLogin(login).map(u -> {
            u.getAuthorities().size();
            return u;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userDAO.getById(id);
    }
}
