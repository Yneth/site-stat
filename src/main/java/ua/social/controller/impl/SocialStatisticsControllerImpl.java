package ua.social.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.social.controller.SocialStatisticsController;
import ua.social.domain.User;
import ua.social.service.SocialStatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocialStatisticsControllerImpl implements SocialStatisticsController {
    private final SocialStatisticsService service;

    @Autowired
    public SocialStatisticsControllerImpl(SocialStatisticsService service) {
        this.service = service;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(@RequestParam("startPosition") int startPosition,
                                              @RequestParam("maxResults") int maxResults) {
        return new ResponseEntity<>(
                service.getPaginatedUsers(startPosition, maxResults), HttpStatus.CREATED
        );
    }
}
