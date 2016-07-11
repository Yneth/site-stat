package ua.abond.social.dao.springdatajpa;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.domain.specification.SiteSessionSpecifications;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/dispatcher-servlet.xml"})
@Transactional
@IntegrationTest
public class SiteSessionSpecificationsTest {

    private static final int COUNT_OF_TEST_SESSIONS = 10;

    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private SiteSessionRepository sessionRepository;

    private Site parent;
    private List<SiteSession> sessions;

    @Before
    public void init() {
        parent = new Site();
        parent.setName("testName");
        parent.setUrl("testName");
        siteRepository.save(parent);

        sessions = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_TEST_SESSIONS; i++) {
            SiteSession siteSession = new SiteSession();
            siteSession.setStartDateTime(ZonedDateTime.now().withHour(i));
            siteSession.setEndDateTime(ZonedDateTime.now().withHour(i + 1));
            siteSession.setSite(parent);
            sessions.add(siteSession);
            sessionRepository.save(siteSession);
        }
        parent.setSiteSessions(sessions);
    }

    @Test
    public void checkCountOfSessions() {
        assertEquals(sessionRepository.findAll(
                SiteSessionSpecifications.sessionsOfSite(parent)
        ).size(), COUNT_OF_TEST_SESSIONS);
    }

    @Test
    public void checkYesterdaysSessions() {
        SiteSession siteSession = new SiteSession();
        siteSession.setStartDateTime(ZonedDateTime.now().minusDays(1));
        siteSession.setEndDateTime(ZonedDateTime.now().minusDays(1).plusHours(6));
        siteSession.setSite(parent);
        sessionRepository.save(siteSession);


        List<SiteSession> list = sessionRepository.findAll(
                SiteSessionSpecifications.yesterdaysSessions()
        );
        assertEquals(1, list.size());

        assertEquals(siteSession, list.get(0));
    }

    @Test
    public void lastMonthSessions() {
        List<SiteSession> expected = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_TEST_SESSIONS; i++) {
            SiteSession siteSession = new SiteSession();
            siteSession.setStartDateTime(ZonedDateTime.now().minusMonths(1));
            siteSession.setEndDateTime(ZonedDateTime.now().minusMonths(1));
            siteSession.setSite(parent);
            expected.add(siteSession);
            sessionRepository.save(siteSession);
        }

        List<SiteSession> list = sessionRepository.findAll(
                SiteSessionSpecifications.sessionsLastMonth()
        );
        assertEquals(COUNT_OF_TEST_SESSIONS, list.size());

        assertEquals(expected, list);
    }

    @Test
    public void testAndSpec() {
        SiteSession siteSession = new SiteSession();
        siteSession.setStartDateTime(ZonedDateTime.now().minusDays(1).withHour(1));
        siteSession.setEndDateTime(ZonedDateTime.now().minusDays(1).withHour(5));
        siteSession.setSite(parent);
        sessionRepository.save(siteSession);

        Site s = new Site();
        s.setUrl("test2");
        s.setName("test2");
        siteRepository.save(s);

        SiteSession ss = new SiteSession();
        ss.setStartDateTime(ZonedDateTime.now().minusDays(1).withHour(1));
        ss.setEndDateTime(ZonedDateTime.now().minusDays(1).withHour(5));
        ss.setSite(s);
        sessionRepository.save(ss);

        List<SiteSession> list = sessionRepository.findAll(
                SiteSessionSpecifications.and(
                        SiteSessionSpecifications.yesterdaysSessions(),
                        SiteSessionSpecifications.sessionsOfSite(parent)
                )
        );
        assertEquals(1, list.size());

        assertEquals(siteSession, list.get(0));
    }
}
