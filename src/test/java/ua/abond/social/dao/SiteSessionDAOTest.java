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
import ua.abond.social.domain.specification.SiteSessionSpecifications;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/db-test-context.xml"})
@Transactional
public class SiteSessionDAOTest {
    private static final int COUNT_OF_TEST_SESSIONS = 10;

    @Autowired
    private SiteDAO siteRepository;
    @Autowired
    private SiteSessionDAO sessionRepository;

    private Site parent;
    private List<SiteSession> sessions;
    private List<SiteSession> lastMonthSessions;

    @Before
    public void init() {
        parent = new Site();
        parent.setName("testName");
        parent.setUrl("testName");
        siteRepository.save(parent);

        sessions = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_TEST_SESSIONS; i++) {
            SiteSession siteSession = new SiteSession();
            siteSession.setStartDateTime(LocalDateTime.now().withHour(i));
            siteSession.setEndDateTime(LocalDateTime.now().withHour(i + 1));
            siteSession.setSite(parent);
            sessions.add(siteSession);
            sessionRepository.save(siteSession);
        }
//        parent.setSiteSessions(sessions);

        lastMonthSessions = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_TEST_SESSIONS; i++) {
            SiteSession siteSession = new SiteSession();
            siteSession.setStartDateTime(LocalDateTime.now().minusMonths(1));
            siteSession.setEndDateTime(LocalDateTime.now().minusMonths(1));
            siteSession.setSite(parent);
            lastMonthSessions.add(siteSession);
            sessionRepository.save(siteSession);
        }
    }

    @Test
    public void checkCountOfSessions() {
        assertEquals(sessionRepository.findAll(
                SiteSessionSpecifications.sessionsOfSite(parent),
                new TestPageable(0, 10, 0, null)
        ).getContent().size(), COUNT_OF_TEST_SESSIONS);
    }

    @Test
    public void checkYesterdaySessions() {
        SiteSession siteSession = new SiteSession();
        siteSession.setStartDateTime(LocalDateTime.now().minusDays(1).withHour(2));
        siteSession.setEndDateTime(LocalDateTime.now().minusDays(1).withHour(5));
        siteSession.setSite(parent);
        sessionRepository.save(siteSession);


        Page<SiteSession> page = sessionRepository.findAll(
                SiteSessionSpecifications.yesterdaySessions(),
                new TestPageable(0, 20, 0, null)
        );
        assertEquals(1, page.getContent().size());

        assertEquals(siteSession, page.getContent().get(0));
    }

    @Test
    public void lastMonthSessions() {
        Page<SiteSession> page = sessionRepository.findAll(
                SiteSessionSpecifications.sessionsLastMonth(),
                new TestPageable(0, 20, 0, null)
        );

        assertEquals(COUNT_OF_TEST_SESSIONS, page.getContent().size());

        assertEquals(lastMonthSessions, page.getContent());
    }

    @Test
    public void testAndSpec() {
        SiteSession siteSession = new SiteSession();
        siteSession.setStartDateTime(LocalDateTime.now().minusDays(1).withHour(1));
        siteSession.setEndDateTime(LocalDateTime.now().minusDays(1).withHour(5));
        siteSession.setSite(parent);
        sessionRepository.save(siteSession);

        Site s = new Site();
        s.setUrl("test2");
        s.setName("test2");
        siteRepository.save(s);

        SiteSession ss = new SiteSession();
        ss.setStartDateTime(LocalDateTime.now().minusDays(1).withHour(1));
        ss.setEndDateTime(LocalDateTime.now().minusDays(1).withHour(5));
        ss.setSite(s);
        sessionRepository.save(ss);

        Page<SiteSession> page = sessionRepository.findAll(
                SiteSessionSpecifications.and(
                        SiteSessionSpecifications.yesterdaySessions(),
                        SiteSessionSpecifications.sessionsOfSite(parent)
                ),
                new TestPageable(0, 20, 0, null)
        );
        assertEquals(1, page.getContent().size());

        assertEquals(siteSession, page.getContent().get(0));
    }
}
