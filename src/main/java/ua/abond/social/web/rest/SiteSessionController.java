package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.service.SiteSessionService;
import ua.abond.social.web.rest.dto.SiteSessionDTO;
import ua.abond.social.web.rest.util.PaginationUtil;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SiteSessionController {
    @Autowired
    private SiteSessionService siteSessionService;

    @RequestMapping("/user/site/{siteId}/session/{sessionId}")
    public ResponseEntity<SiteSessionDTO> getById(@PathVariable("sessionId") Long sessionId) {
        Optional<SiteSession> session = siteSessionService.getById(sessionId);

        return session.map(SiteSessionDTO::new)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/user/site/{siteId}/session")
    public ResponseEntity<List<SiteSessionDTO>> getAllSessionsForSiteWithId(
            @PathVariable("siteId")Long siteId,
            Pageable pageable) throws URISyntaxException {
        Page<SiteSession> page = siteSessionService.getBySiteId(siteId, pageable);

        // TODO: it is ugly change later
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/user/site/" + siteId + "/session");

        return new ResponseEntity<>(page.getContent()
                .stream()
                .map(SiteSessionDTO::new)
                .collect(Collectors.toList()), headers, HttpStatus.OK);
    }
}
