package ua.abond.social.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.dao.UserDAO;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.User;
import ua.abond.social.service.SiteSessionService;
import ua.abond.social.web.rest.dto.SiteSessionDTO;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/dispatcher-servlet.xml"})
//@DatabaseSetup
//@WebAppConfiguration
@Transactional
public class SiteSessionControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SiteSessionService siteSessionService;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSocialNetworkMock;

    private User user;
    private Site site;
    private SiteSessionDTO session;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SiteSessionController resource = new SiteSessionController();
        ReflectionTestUtils.setField(resource, "siteSessionService", siteSessionService);
        this.restSocialNetworkMock = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .build();
    }

    @Before
    public void initTest() {
        user = userDAO.findOneByLogin("admin").get();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("admin", "admin");
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        site = new Site();
        site.setUrl("");
        site.setName("");
        site.setUser(user);
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void saveSession() throws Exception {
        session = new SiteSessionDTO();
        session.setSiteId(site.getId());
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now().plusHours(1));

        restSocialNetworkMock.perform(post("/api/site/" + site.getId() + "/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(session))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.siteId").value(session.getSiteId()));
    }

    @Test
    public void deleteById() throws Exception {

    }

    @Test
    public void getAllSessionsForSiteWithId() throws Exception {

    }

}