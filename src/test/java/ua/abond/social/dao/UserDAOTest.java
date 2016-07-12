package ua.abond.social.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.dao.AuthorityDAO;
import ua.abond.social.domain.Authority;
import ua.abond.social.domain.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/db-test-context.xml"})
@Transactional
public class UserDAOTest {
    private User testUser;
    private Authority roleUser;
    private Authority roleAdmin;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AuthorityDAO authorityDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        testUser = new User();
        testUser.setEmail("test@test.com");
        testUser.setFirstName("TestFirstName");
        testUser.setLastName("TestLastName");
        testUser.setActivated(true);
        testUser.setLogin("TestLogin");
        testUser.setPassword(passwordEncoder.encode("TestPassword"));

        roleUser = authorityDAO.findOneWithName("ROLE_USER").get();
        roleAdmin = authorityDAO.findOneWithName("ROLE_ADMIN").get();

        Set<Authority> auths = new HashSet<>();
        auths.add(roleUser);
        testUser.setAuthorities(auths);

        userDAO.save(testUser);
    }

    @Test
    public void findOneByLoginWithAuthorities() throws Exception {
        Optional<User> actualOpt = userDAO.findOneByLoginWithAuthorities(testUser.getLogin());
        assertNotEquals(Optional.empty(), actualOpt);
        assertEquals(testUser, actualOpt.get());
        User actual = actualOpt.get();
        assertEquals(testUser, actual);
        assertEquals(testUser.getAuthorities(), actual.getAuthorities());
    }

    @Test
    public void findOneByIdWithAuthorities()  throws Exception {
        Optional<User> actualOpt = userDAO.findOneByIdWithAuthorities(testUser.getId());
        assertNotEquals(Optional.empty(), actualOpt);
        User actual = actualOpt.get();
        assertEquals(testUser, actual);
        assertEquals(testUser.getAuthorities(), actual.getAuthorities());
    }
}