package ua.abond.social.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.dao.util.TestPageable;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.domain.SiteStatistic;
import ua.abond.social.domain.User;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/db-test-context.xml"})
@Transactional
public class SiteDAOTest {
    private static final String TEST_SITE_NAME = "TEST_NAME";
    private static final String TEST_SITE_URL = "TEST_URL";

    private User adminUser;
    private Site testSite;

    @Autowired
    private SiteDAO siteDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SiteSessionDAO siteSessionDAO;

    @Before
    public void setUp() throws Exception {
        testSite = new Site();
        adminUser = userDAO.findOneByLogin("admin").get();

        testSite.setUrl(TEST_SITE_URL);
        testSite.setName(TEST_SITE_NAME);
        testSite.setUser(adminUser);

        siteDAO.save(testSite);
    }

    @Test
    public void findByUserLogin() throws Exception {
        Page<Site> site = siteDAO.findByUserLogin(adminUser.getLogin(), new TestPageable(0, 10, 0, null));

        assertNotNull(site);
        List<Site> content = site.getContent();
        assertNotNull(content);
        assertEquals(1, content.size());

        Site actual = content.get(0);
        assertEquals(testSite.getId(), actual.getId());
        assertEquals(testSite.getName(), actual.getName());
        assertEquals(testSite.getUrl(), actual.getUrl());
    }

    @Test
    public void findByUserId() throws Exception {
        Page<Site> site = siteDAO.findByUserId(adminUser.getId(), new TestPageable(0, 10, 0, null));

        assertNotNull(site);
        List<Site> content = site.getContent();
        assertNotNull(content);
        assertEquals(1, content.size());

        Site actual = content.get(0);
        assertEquals(testSite.getId(), actual.getId());
        assertEquals(testSite.getName(), actual.getName());
        assertEquals(testSite.getUrl(), actual.getUrl());
    }

    @Test
    @Transactional()
    public void getStatisticsByUserId() throws Exception {
        SiteSession s = new SiteSession();
        s.setDuration(10l);
        s.setStartDateTime(ZonedDateTime.now());
        s.setEndDateTime(ZonedDateTime.now().plusMinutes(10));
        s.setSite(testSite);
        siteSessionDAO.save(s);

        Page<SiteStatistic> siteStat =
                siteDAO.getStatisticsByUserId(adminUser.getId(), new TestPageable(0, 10, 0, null));

        assertNotNull(siteStat);
        List<SiteStatistic> content = siteStat.getContent();
        assertNotNull(content);
        assertEquals(1, content.size());

        SiteStatistic actual = content.get(0);

        assertEquals(testSite.getId(), actual.getSite().getId());
        assertEquals(testSite.getName(), actual.getSite().getName());
        assertEquals(testSite.getUrl(), actual.getSite().getUrl());
        assertEquals(s.getDuration(), actual.getUsageDuration());


        System.out.println(actual.getSite().getId());
        System.out.println(actual.getSite().getName());
        System.out.println(actual.getSite().getUrl());
    }
}