package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.abond.social.domain.Site;
import ua.abond.social.security.SecurityUtils;
import ua.abond.social.service.SiteService;
import ua.abond.social.web.rest.dto.SiteDTO;
import ua.abond.social.web.rest.util.PaginationUtil;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/user/site/{siteId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> getSiteById(@PathVariable("siteId") Long id) {
        return siteService.getByIdWithSessions(id)
                .map(sc -> new ResponseEntity<>(new SiteDTO(sc), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user/site",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SiteDTO>> getSitesForAuthUser(Pageable pageable)
            throws URISyntaxException {
        Page<Site> page = siteService.getByUserId(SecurityUtils.getCurrentUserId(), pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user/site");

        return new ResponseEntity<>(
                page.getContent().stream()
                        .map(s -> {
                            SiteDTO siteDTO = new SiteDTO();
                            siteDTO.setId(s.getId());
                            siteDTO.setUrl(s.getUrl());
                            siteDTO.setName(s.getName());
                            return siteDTO;
                        })
                        .collect(Collectors.toList()),
                headers,
                HttpStatus.OK);
    }
}
