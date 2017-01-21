package ua.abond.social.web.rest;

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
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.dao.UserDAO;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.User;
import ua.abond.social.service.SiteService;
import ua.abond.social.web.rest.dto.LoginDTO;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/dispatcher-servlet.xml"})
//@DatabaseSetup
//@WebAppConfiguration
@Transactional
public class SiteControllerTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_URL = "BBBBB";
    private static final String UPDATED_URL = "AAAAA";

    @Autowired
    private SiteDAO siteDAO;
    @Autowired
    private SiteService siteService;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc mockMvc;

    private Site site;

    private User testUser;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SiteController resource = new SiteController();
        ReflectionTestUtils.setField(resource, "siteService", siteService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .build();
    }

    @Before
    public void initTest() {
        testUser = userDAO.findOneByLogin("admin").get();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("admin", "admin");
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        site = new Site();
        site.setUrl(DEFAULT_URL);
        site.setName(DEFAULT_NAME);
        site.setUser(testUser);
    }

    @Test
    public void getSiteById() throws Exception {
        siteDAO.save(site);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("admin");
        loginDTO.setPassword("admin");

        mockMvc
                .perform(post("/api/authenticate", loginDTO));

        mockMvc
                .perform(get("/api/user/site/{siteId}", site.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
                .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
}