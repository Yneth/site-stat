package ua.social.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.social.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.debug("Authenticating {}", login);

        return userService.getUserByLogin(login).map(u -> {
            if (!u.getActivated()) {
                throw new UserNotActivatedException("User " + login + " was not activated");
            }
            List<GrantedAuthority> authorities = u.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .collect(Collectors.toList());
            return new User(u.getLogin(), u.getPassword(), authorities);
        }).orElseThrow(() ->
                new UsernameNotFoundException("User with " + login + " was not found.")
        );
    }
}
