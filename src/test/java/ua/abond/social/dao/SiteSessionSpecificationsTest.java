package ua.abond.social.dao;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.dao.springdatajpa.SiteRepository;
import ua.abond.social.dao.springdatajpa.SiteSessionRepository;
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
            siteSession.setStartDateTime(ZonedDateTime.now().withHour(i));
            siteSession.setEndDateTime(ZonedDateTime.now().withHour(i + 1));
            siteSession.setSite(parent);
            sessions.add(siteSession);
            sessionRepository.save(siteSession);
        }
        parent.setSiteSessions(sessions);

        lastMonthSessions = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_TEST_SESSIONS; i++) {
            SiteSession siteSession = new SiteSession();
            siteSession.setStartDateTime(ZonedDateTime.now().minusMonths(1));
            siteSession.setEndDateTime(ZonedDateTime.now().minusMonths(1));
            siteSession.setSite(parent);
            lastMonthSessions.add(siteSession);
            sessionRepository.save(siteSession);
        }
    }

    @Test
    public void checkCountOfSessions() {
        assertEquals(sessionRepository.findAll(
                SiteSessionSpecifications.sessionsOfSite(parent),
                new PageableImpl(0, 10, 0, null)
        ).getContent().size(), COUNT_OF_TEST_SESSIONS);
    }

    @Test
    public void checkYesterdaySessions() {
        SiteSession siteSession = new SiteSession();
        siteSession.setStartDateTime(ZonedDateTime.now().minusDays(1));
        siteSession.setEndDateTime(ZonedDateTime.now().minusDays(1).plusHours(6));
        siteSession.setSite(parent);
        sessionRepository.save(siteSession);


        Page<SiteSession> page = sessionRepository.findAll(
                SiteSessionSpecifications.yesterdaySessions(),
                new PageableImpl(0, 20, 0, null)
        );
        assertEquals(1, page.getContent().size());

        assertEquals(siteSession, page.getContent().get(0));
    }

    @Test
    public void lastMonthSessions() {
        Page<SiteSession> page = sessionRepository.findAll(
                SiteSessionSpecifications.sessionsLastMonth(),
                new PageableImpl(0, 20, 0, null)
        );

        assertEquals(COUNT_OF_TEST_SESSIONS, page.getContent().size());

        assertEquals(lastMonthSessions, page.getContent());
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

        Page<SiteSession> page = sessionRepository.findAll(
                SiteSessionSpecifications.and(
                        SiteSessionSpecifications.yesterdaySessions(),
                        SiteSessionSpecifications.sessionsOfSite(parent)
                ),
                new PageableImpl(0, 20, 0, null)
        );
        assertEquals(1, page.getContent().size());

        assertEquals(siteSession, page.getContent().get(0));
    }

    private static class PageableImpl implements Pageable {
        private int pageNumber;
        private int pageSize;
        private int offset;
        private Sort sort;

        public PageableImpl(int pageNumber, int pageSize, int offset, Sort sort) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.offset = offset;
            this.sort = sort;
        }

        @Override
        public int getPageNumber() {
            return pageNumber;
        }

        @Override
        public int getPageSize() {
            return pageSize;
        }

        @Override
        public int getOffset() {
            return offset;
        }

        @Override
        public Sort getSort() {
            return sort;
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    }
}
