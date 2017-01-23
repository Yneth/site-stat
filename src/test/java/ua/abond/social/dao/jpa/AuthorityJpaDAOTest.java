package ua.abond.social.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.abond.social.domain.Authority;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/jpa-db-context.xml"})
public class AuthorityJpaDAOTest {
    @Autowired
    private AuthorityJpaDAO authorityJpaDAO;

    @Test
    public void testSuccessfulFindByName() {
        final String adminRole = "ROLE_ADMIN";
        Optional<Authority> admin = authorityJpaDAO.findByName(adminRole);
        assertTrue(admin.isPresent());
        assertTrue(adminRole.equals(admin.orElse(null).getName()));
    }

    @Test
    public void testFindByNameNonExisting() {
        Optional<Authority> byName = authorityJpaDAO.findByName(null);
        assertFalse(byName.isPresent());
    }
}