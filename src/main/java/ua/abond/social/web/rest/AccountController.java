package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.abond.social.config.Constants;
import ua.abond.social.security.SecurityUtils;
import ua.abond.social.security.TokenProvider;
import ua.abond.social.service.UserService;
import ua.abond.social.web.rest.dto.ManagedUserDTO;
import ua.abond.social.web.rest.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> isAuthenticated() {
        return userService.getCurrentUser()
                .map(u -> new ResponseEntity<>(new UserDTO(u), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<Void> isAuthenticated(@Valid @RequestBody UserDTO userDTO) {
        userService.updateUserInformation(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "updated site");
        headers.add("X-socialStatApp-params", SecurityUtils.getCurrentUserId().toString());
        return ResponseEntity.ok().headers(headers).build();
    }

    @RequestMapping(value = "/account/change_password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody String password) {
        if (!isValidPassword(password)) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
        }
        userService.changePassword(password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody ManagedUserDTO userDTO, HttpServletResponse response) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        return userService.getUserByLogin(userDTO.getLogin())
                .map(u -> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                .orElseGet(() -> userService.getUserByEmail(userDTO.getEmail())
                        .map(u -> new ResponseEntity<>(
                                "e-mail address already in use",
                                textPlainHeaders,
                                HttpStatus.BAD_REQUEST)
                        )
                        .orElseGet(() -> {
                            userService.createUser(userDTO);
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        })
                );
    }

    private boolean isValidPassword(String password) {
        return StringUtils.hasText(password)
                && password.length() >= Constants.PASSWORD_MIN_LENGTH
                && password.length() <= Constants.PASSWORD_MAX_LENGTH;
    }
}
