package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.abond.social.service.SocialNetworkService;
import ua.abond.social.web.rest.dto.SocialNetworkDTO;

@RestController
@RequestMapping("/api")
public class SocialNetworkResource {

    @Autowired
    private SocialNetworkService socialNetworkService;

    @RequestMapping(value = "/network/{id}", method = RequestMethod.GET)
    public ResponseEntity<SocialNetworkDTO> getNetworkById(@PathVariable("id") Long id) {
        return socialNetworkService.getById(id)
                .map(sc -> new ResponseEntity<>(new SocialNetworkDTO(sc), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
