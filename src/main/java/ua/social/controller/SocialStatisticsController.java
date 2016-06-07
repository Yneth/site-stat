package ua.social.controller;

import org.springframework.http.ResponseEntity;
import ua.social.domain.User;

import java.util.List;

public interface SocialStatisticsController {
    ResponseEntity<List<User>> getUsers(int startPosition, int maxResults);
}
