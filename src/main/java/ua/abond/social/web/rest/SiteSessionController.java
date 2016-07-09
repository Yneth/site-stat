package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.abond.social.service.SiteSessionService;

@RestController
@RequestMapping("/api")
public class SiteSessionController {
    @Autowired
    private SiteSessionService siteSessionService;


}
