package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.abond.social.config.Constants;
import ua.abond.social.security.TokenProvider;
import ua.abond.social.security.acl.impl.User;
import ua.abond.social.web.rest.dto.JwtToken;
import ua.abond.social.web.rest.dto.LoginDTO;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/")
public class UserJwtController {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "authenticate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            boolean rememberMe = (loginDTO.isRememberMe() == null) ? false : loginDTO.isRememberMe();

            String token = tokenProvider.createToken(authentication, ((User) authentication.getPrincipal()).getId(), rememberMe);
            response.addHeader(Constants.AUTHORIZATION_HEADER, Constants.BEARER + token);
//            response.addHeader("Access-Control-Allow-Origin", "*");

            return ResponseEntity.ok(new JwtToken(token));
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>(Collections.singletonMap(
                    "AuthenticationException", exception.getLocalizedMessage()
            ), HttpStatus.UNAUTHORIZED);
        }
    }
}
