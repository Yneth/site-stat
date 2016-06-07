package ua.social.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.social.service.UserService;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.debug("Authenticating {}", username);
        return userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
