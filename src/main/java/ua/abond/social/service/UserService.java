package ua.abond.social.service;

import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.User;
import ua.abond.social.web.rest.dto.ManagedUserDTO;

import java.util.Optional;

@Transactional
public interface UserService {
    User createUser(ManagedUserDTO user);

    User createUserInformation(String login, String password, String firstName, String lastName, String email,
                               String langKey);

    Optional<User> activateRegistration(String key);

    void updateUserInformation(String firstName, String secondName, String email);

    void deleteUserInformation(String login);

    @Transactional(readOnly = true)
    Optional<User> getCurrentUser();

    @Transactional(readOnly = true)
    Optional<User> getUserByLogin(String login);

    @Transactional(readOnly = true)
    Optional<User> getUserByEmail(String email);

    @Transactional(readOnly = true)
    Optional<User> getUserByIdWithAuthorities(Long id);

    @Transactional(readOnly = true)
    Optional<User> getUserByLoginWithAuthorities(String login);

    @Transactional(readOnly = true)
    Optional<User> getUserById(Long id);
}
