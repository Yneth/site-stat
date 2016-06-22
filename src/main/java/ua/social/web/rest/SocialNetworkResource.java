package ua.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.social.dao.SocialNetworkDAO;
import ua.social.web.rest.dto.SocialNetworkDTO;

@RestController
@RequestMapping("/api")
public class SocialNetworkResource {

    @Autowired
    private SocialNetworkDAO socialNetworkDAO;

    @RequestMapping(value = "/network/{id}", method = RequestMethod.GET)
    public ResponseEntity<SocialNetworkDTO> getNetworkById(@PathVariable("id") Long id) {
        return socialNetworkDAO.getById(id)
                .map(sc -> new ResponseEntity<>(new SocialNetworkDTO(sc), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
