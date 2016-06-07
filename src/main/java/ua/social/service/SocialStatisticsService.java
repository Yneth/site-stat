package ua.social.service;

import ua.social.domain.User;

import java.util.List;

public interface SocialStatisticsService {
    List<User> getPaginatedUsers(int startPosition, int maxResults);
}
