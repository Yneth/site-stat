package ua.social.service;

import ua.social.domain.User;
import ua.social.web.rest.dto.ManagedUserDTO;

import java.util.Optional;

public interface UserService {
    Optional<User> createUserInformation(ManagedUserDTO user);

    Optional<User> activateRegistration(String key);

    Optional<User> updateUserInformation(String firstName, String secondName, String email, String langKey);

    Optional<User> deleteUserInformation(String login);

    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(Long id);
}
