package ua.social.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class SocialController {

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public RedirectView signUp() {
        throw new UnsupportedOperationException();
    }
}