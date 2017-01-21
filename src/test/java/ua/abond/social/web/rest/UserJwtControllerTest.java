package ua.abond.social.web.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.abond.social.config.Constants;
import ua.abond.social.security.TokenProvider;
import ua.abond.social.security.acl.impl.User;
import ua.abond.social.web.rest.dto.LoginDTO;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserJwtControllerTest {
    @Mock
    private HttpServletResponse response;

    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private Authentication auth;

    @InjectMocks
    private UserJwtController controller = new UserJwtController();

    @Test
    public void testRightSuccessCode() throws Exception {
        mockUserToAuthentication();
        mockAuthToManager();

        when(authManager.authenticate(any(Authentication.class))).thenReturn(auth);
        ResponseEntity<?> responseEntity = controller.authenticate(new LoginDTO(), response);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testRightErrorCode() throws Exception {
        when(authManager.authenticate(any(Authentication.class))).
                thenThrow(mock(AuthenticationException.class));
        ResponseEntity<?> responseEntity = controller.authenticate(new LoginDTO(), response);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    @Test
    public void testAuthenticationIsSetToHolderOnSuccess() throws Exception {
        mockUserToAuthentication();
        mockAuthToManager();

        controller.authenticate(new LoginDTO(), response);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(auth, authentication);
    }

    @Test
    public void testAuthenticationHeaderIsSetOnSuccess() throws Exception {
        final String TEST_TOKEN = "some token out here";
        mockUserToAuthentication();
        mockAuthToManager();
        when(tokenProvider.createToken(any(Authentication.class), any(Long.class), anyBoolean())).
                thenReturn(TEST_TOKEN);

        controller.authenticate(new LoginDTO(), response);

        verify(response).addHeader(eq(Constants.AUTHORIZATION_HEADER), eq(Constants.BEARER + TEST_TOKEN));
    }

    private void mockAuthToManager() throws Exception {
        when(authManager.authenticate(any(Authentication.class))).thenReturn(auth);
    }

    private void mockUserToAuthentication() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(0L);
        when(auth.getPrincipal()).thenReturn(user);
    }
}