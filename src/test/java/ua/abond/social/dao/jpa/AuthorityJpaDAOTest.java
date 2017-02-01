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
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.Authority;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/jpa-db-context.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@Transactional
public class AuthorityJpaDAOTest {
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private AuthorityJpaDAO authorityJpaDAO;

    @Test
    public void testSaveAuthorityShouldSaveToTheDatabase() {
        String role = "ROLE_TEST";

        Authority authority = new Authority();
        authority.setName(role);
        authorityJpaDAO.save(authority);

        Authority actual = authorityJpaDAO.findByName(role).orElse(null);
        assertNotNull(actual);
        assertThat(actual.getName(), is(authority.getName()));
    }

    @Test
    @DatabaseSetup("/ds/authority-ds.xml")
    public void testUpdateAuthorityShouldUpdateSuccessfully() {
        final String newRole = "ROLE_TEST";

        Authority authority = new Authority();
        authority.setId(-1L);
        authority.setName(newRole);
        authorityJpaDAO.save(authority);

        assertNull(authorityJpaDAO.findByName(ROLE_ADMIN).orElse(null));
        assertNotNull(authorityJpaDAO.findByName(newRole).orElse(null));
    }

    @Test
    @DatabaseSetup("/ds/authority-ds.xml")
    public void testSuccessfulFindByName() {
        Optional<Authority> admin = authorityJpaDAO.findByName(ROLE_ADMIN);
        assertTrue(admin.isPresent());
        assertTrue(ROLE_ADMIN.equals(admin.orElse(null).getName()));
    }

    @Test
    public void testFindByNameNonExisting() {
        Optional<Authority> byName = authorityJpaDAO.findByName(null);
        assertFalse(byName.isPresent());
    }
}