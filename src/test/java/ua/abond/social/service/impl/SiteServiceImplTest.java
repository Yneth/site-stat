package ua.abond.social.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.domain.Site;
import ua.abond.social.service.SiteService;
import ua.abond.social.web.rest.dto.SiteDTO;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SiteServiceImplTest {
    @Mock
    private SiteDAO siteDAO;
    @Mock
    private ModelMapper modelMapper;

    private SiteService siteService;

    @Before
    public void setUp() throws Exception {
        siteService = new SiteServiceImpl(siteDAO, modelMapper);
    }

    @Test
    public void testCreate() throws Exception {
        siteService.createSite(new SiteDTO());
        verify(siteDAO).save(any(Site.class));
    }

    @Test
    public void testDeleteById() {
        Long id = -1L;
        siteService.deleteById(id);
        verify(siteDAO).deleteById(any(Long.class));
    }
}