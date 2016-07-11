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
import ua.abond.social.web.rest.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;


// TODO: implement httpheaders builder
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
                .map(sc -> new ResponseEntity<>(SiteDTO.from(sc).exceptUser(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user/site/{siteId}",
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

    @RequestMapping(value = "/user/site",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteDTO> createSiteForUser(@RequestBody SiteDTO siteDTO)
            throws URISyntaxException {
        Site site = siteService.createSite(siteDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "created site");
        headers.add("X-socialStatApp-params", site.getId().toString());
        return ResponseEntity.created(new URI("api/user/site" + site.getId()))
                .headers(headers)
                .body(new SiteDTO(site));
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
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/user/site/{siteId}",
            method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<Void> deleteSiteById(@PathVariable("siteId") Long siteId) {
        siteService.deleteById(siteId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "deleted site");
        headers.add("X-socialStatApp-params", siteId.toString());
        return ResponseEntity.ok().headers(headers).build();
    }
}
