package ua.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.social.dao.UserDAO;
import ua.social.domain.User;
import ua.social.service.SocialStatisticsService;

import java.util.List;

@Service
public class SocialStatisticsServiceImpl implements SocialStatisticsService {
    private final UserDAO userDAO;

    @Autowired
    public SocialStatisticsServiceImpl(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getPaginatedUsers(int startPosition, int maxResults) {
        return userDAO.list(startPosition, maxResults);
    }
}
