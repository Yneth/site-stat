package ua.abond.social.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.User;
import ua.abond.social.security.SecurityUtils;
import ua.abond.social.service.SiteService;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.web.rest.dto.SiteDTO;

import java.util.Optional;

@Service
public class SiteServiceImpl implements SiteService {
    private final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private SiteDAO siteDAO;

    @Override
    public Site createSite(SiteDTO siteDTO) {
        log.debug("Request to save Site {}", siteDTO);
        Site site = new Site();
        site.setUrl(siteDTO.getUrl());
        site.setName(siteDTO.getName());
        User owner = new User();
        owner.setId(SecurityUtils.getCurrentUserId());
        site.setUser(owner);
        return siteDAO.save(site);
    }

    @Override
    public Site updateSite(SiteDTO siteDTO) {
        log.debug("Request to update SiteDTO {}", siteDTO);
        Site site = new Site();
        site.setId(siteDTO.getId());
        site.setUrl(siteDTO.getUrl());
        site.setName(siteDTO.getName());
        User owner = new User();
        owner.setId(SecurityUtils.getCurrentUserId());
        site.setUser(owner);
        return siteDAO.save(site);
    }

    @Override
    public void deleteSite(Site Site) {
        log.debug("Request to delete Site {}", Site);
        siteDAO.delete(Site);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Request to delete Site with id Long {}", id);
        siteDAO.deleteById(id);
    }

    @Override
    public Optional<Site> getByIdWithSessions(Long id) {
        log.debug("Request to read social Site with id Long {}", id);
        Optional<Site> byId = siteDAO.getById(id).map(sn -> {
            sn.getSiteSessions().size();
            return sn;
        });
        return byId;
    }


    @Override
    public Page<Site> getByUserId(Long id, Pageable pageable) {
        log.debug("Request to read all Site {} for requested User id", id);
        return siteDAO.findByUserId(id, pageable);
    }
}
