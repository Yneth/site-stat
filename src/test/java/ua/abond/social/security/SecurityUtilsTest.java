package ua.abond.social.security;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.Assert.*;

public class SecurityUtilsTest {

    @Test
    public void testGetCurrentUserLogin() {
        final String expectedLogin = "admin";
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(expectedLogin, "admin"));
        String actualLogin = SecurityUtils.getCurrentUserLogin();
        assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void testFailGetCurrentUserLogin() {
        String actualLogin = SecurityUtils.getCurrentUserLogin();
        assertEquals(null, actualLogin);
    }
}