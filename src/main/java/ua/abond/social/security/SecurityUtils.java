package ua.abond.social.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ua.abond.social.security.acl.impl.User;

public class SecurityUtils {

    public static String getCurrentUserLogin() {
        Authentication authentication = getAuthentication();
        String login = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
             if (principal instanceof User) {
                User user = (User) principal;
                login = user.getUsername();
            }
            else if (principal instanceof UserDetails) {
                UserDetails details = (UserDetails) principal;
                login = details.getUsername();
            } else if (principal instanceof String) {
                login = (String) principal;
            }
        }
        return login;
    }

    public static Long getCurrentUserId() {
        Authentication authentication = getAuthentication();
        Long id = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                id = user.getId();
            }
        }
        return id;
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
