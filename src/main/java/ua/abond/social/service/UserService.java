package ua.abond.social.service;

import ua.abond.social.domain.User;
import ua.abond.social.web.rest.dto.ManagedUserDTO;

import java.util.Optional;

public interface UserService {
    User createUser(ManagedUserDTO user);

    User createUserInformation(String login, String password, String firstName, String lastName, String email,
                               String langKey);

    Optional<User> activateRegistration(String key);

    void updateUserInformation(String firstName, String secondName, String email, String langKey);

    void deleteUserInformation(String login);

    Optional<User> getCurrentUser();

    Optional<User> getUserByLogin(String login);

    Optional<User> getUserByLoginWithAuthorities(String login);

    Optional<User> getUserById(Long id);
}
