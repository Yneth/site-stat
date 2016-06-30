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
import ua.abond.social.domain.SocialNetwork;
import ua.abond.social.security.SecurityUtils;
import ua.abond.social.service.SocialNetworkService;
import ua.abond.social.web.rest.dto.SocialNetworkDTO;
import ua.abond.social.web.rest.util.PaginationUtil;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SocialNetworkResource {

    @Autowired
    private SocialNetworkService socialNetworkService;

    @RequestMapping(value = "/network/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocialNetworkDTO> getNetworkById(@PathVariable("id") Long id) {

        return socialNetworkService.getByIdWithSessions(id)
                .map(sc -> new ResponseEntity<>(new SocialNetworkDTO(sc), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user/network",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SocialNetworkDTO>> getSocialNetworksForAuthUser(Pageable pageable)
            throws URISyntaxException {
        Page<SocialNetwork> page = socialNetworkService.getByUserId(SecurityUtils.getCurrentUserId(), pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user/network");

        return new ResponseEntity<>(
                page.getContent().stream()
                        .map(s -> {
                            SocialNetworkDTO socialNetworkDTO = new SocialNetworkDTO();
                            socialNetworkDTO.setId(s.getId());
                            socialNetworkDTO.setUrl(s.getUrl());
                            socialNetworkDTO.setName(s.getName());
                            return socialNetworkDTO;
                        })
                        .collect(Collectors.toList()),
                headers,
                HttpStatus.OK);
    }
}
