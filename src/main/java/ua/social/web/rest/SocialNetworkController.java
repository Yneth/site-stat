package ua.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;

@RestController
@RequestMapping("/api")
public class SocialNetworkController {

    @Autowired
    private SocialNetworkDAO socialNetworkDAO;

    @RequestMapping(value = "/network/{id}", method = RequestMethod.GET)
    public SocialNetwork getNetworkById(@PathVariable("id") Long id) {
        return socialNetworkDAO.getById(id).orElseGet(null);
    }
}
