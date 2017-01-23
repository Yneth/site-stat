package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.abond.social.domain.Site;
import ua.abond.social.security.SecurityUtils;
import ua.abond.social.service.SiteService;
import ua.abond.social.web.rest.dto.SiteDTO;
import ua.abond.social.web.rest.dto.SiteSummary;
import ua.abond.social.web.rest.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


// TODO: implement httpheaders builder
@RestController
@RequestMapping("/api")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/site/{siteId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> getSiteById(@PathVariable("siteId") Long id) {
        return siteService.getById(id)
                .map(sc -> new ResponseEntity<>(SiteDTO.from(sc).exceptUserAndSessions(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/site/{siteId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> updateSite(@RequestBody SiteDTO siteDTO) {
        Site site = siteService.updateSite(siteDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "updated site");
        headers.add("X-socialStatApp-params", site.getId().toString());
        return ResponseEntity.ok()
                .headers(headers)
                .body(new SiteDTO(site));
    }

    @RequestMapping(value = "/site",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> createSiteForUser(@RequestBody SiteDTO siteDTO)
            throws URISyntaxException {
        Site site = siteService.createSite(siteDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "created site");
        headers.add("X-socialStatApp-params", site.getId().toString());
        return ResponseEntity.created(new URI("api/site" + site.getId()))
                .headers(headers)
                .body(new SiteDTO(site));
    }

    @RequestMapping(value = "/site",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SiteSummary>> getSitesForAuthUser(Pageable pageable)
            throws Exception {
        Page<SiteSummary> page = siteService.getStatisticsByUserId(SecurityUtils.getCurrentUserId(), pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/site");

        return new ResponseEntity<>(
                page.getContent(),
                headers,
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/site/{siteId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSiteById(@PathVariable("siteId") Long siteId) {
        siteService.deleteById(siteId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "deleted site");
        headers.add("X-socialStatApp-params", siteId.toString());
        return ResponseEntity.ok().headers(headers).build();
    }
}
