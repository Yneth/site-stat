package ua.abond.social.dao.jpa;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ua.abond.social.domain.Authority;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/jpa-db-context.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public class AuthorityJpaDAOTest {
    @Autowired
    private AuthorityJpaDAO authorityJpaDAO;

    @Test
    @DatabaseSetup("/ds/authority-ds.xml")
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