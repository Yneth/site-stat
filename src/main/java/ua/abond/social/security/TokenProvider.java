package ua.abond.social.security;

import org.springframework.security.core.Authentication;

public interface TokenProvider {
    String createToken(Authentication authentication, Long id, boolean rememberMe);

    boolean validateToken(String token);

    Authentication getAuthentication(String token);
}
