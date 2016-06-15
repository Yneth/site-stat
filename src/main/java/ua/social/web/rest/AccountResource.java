package ua.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.social.service.UserService;
import ua.social.web.rest.dto.UserDTO;

@RestController
@RequestMapping("/api")
public class AccountResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> isAuthenticated() {
        return userService.getCurrentUser()
                .map(u -> new ResponseEntity<>(new UserDTO(u), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
