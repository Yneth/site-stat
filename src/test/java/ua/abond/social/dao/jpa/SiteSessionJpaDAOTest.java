package ua.abond.social.dao.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.SiteSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/jpa-db-context.xml"})
public class SiteSessionJpaDAOTest {
    @Autowired
    private SiteSessionJpaDAO siteSessionDAO;

    @Test
    public void testSuccessfulGetBySiteId() throws Exception {
        Page<SiteSession> bySiteId = siteSessionDAO.getBySiteId(1L, new PageRequest(0, 10));

        assertFalse(bySiteId.getContent().isEmpty());
        assertEquals(siteSessionDAO.getById(1L).orElse(null), bySiteId.getContent().get(0));
    }
}