package ua.abond.social.web.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/api/test1", method = RequestMethod.GET)
    public String test() {
        return "{\"message\":\"hi\"}";
    }
}
