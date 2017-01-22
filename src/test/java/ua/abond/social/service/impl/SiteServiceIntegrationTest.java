package ua.abond.social.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.abond.social.security.acl.impl.User;
import ua.abond.social.service.SiteService;
import ua.abond.social.web.rest.dto.SiteDTO;

import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/dispatcher-servlet.xml"})
public class SiteServiceIntegrationTest {
    @Autowired
    private SiteService siteService;

    @Test
    public void testCreateSite() {
        setAdminToContext();

        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setName("name");
        siteDTO.setUrl("www.com.com");
        siteService.createSite(siteDTO);
    }

    @Test
    public void testUpdateSite() {
        setAdminToContext();

        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setId(-1L);
        siteDTO.setName("name");
        siteDTO.setUrl("www.com.com");
        siteService.updateSite(siteDTO);
    }

    private void setAdminToContext() {
        User user = new User(1L, "admin", "admin", new HashSet<GrantedAuthority>() {{
            add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }});
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, user.getUsername(), user.getAuthorities())
        );
    }
}
