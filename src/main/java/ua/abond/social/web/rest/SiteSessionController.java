package ua.abond.social.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.service.SiteSessionService;
import ua.abond.social.web.rest.dto.SiteSessionDTO;
import ua.abond.social.web.rest.util.PaginationUtil;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SiteSessionController {
    @Autowired
    private SiteSessionService siteSessionService;

    @RequestMapping(value = "/user/site/{siteId}/session/{sessionId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteSessionDTO> getById(@PathVariable("sessionId") Long sessionId) {
        Optional<SiteSession> session = siteSessionService.getById(sessionId);

        return session.map(SiteSessionDTO::new)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user/site/{siteId}/session/{sessionId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("sessionId") Long sessionId) {
        siteSessionService.deleteById(sessionId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-socialStatApp-alert", "deleted site session");
        headers.add("X-socialStatApp-params", sessionId.toString());
        return ResponseEntity.ok().headers(headers).build();
    }

    @RequestMapping(value = "/user/site/{siteId}/session",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SiteSessionDTO>> getAllSessionsForSiteWithId(
            @PathVariable("siteId") Long siteId,
            @RequestParam(value = "from", required = false) LocalDateTime from,
            @RequestParam(value = "to", required = false) LocalDateTime to,
            Pageable pageable) throws URISyntaxException {
        Page<SiteSession> page = null;
        if (from == null || to == null) {
            page = siteSessionService.getBySiteId(siteId, pageable);
        } else if (from != null && to != null) {
            LocalDateTime before = from;
            LocalDateTime after = to;

            if (before.isAfter(after)) {
                LocalDateTime temp = before;
                before = after;
                after = temp;
            }
            page = siteSessionService.getBySiteIdBetweenDates(siteId, before, after, pageable);
        }

        // TODO: remove hardcoded uri
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/user/site/" + siteId + "/session");

        return new ResponseEntity<>(page.getContent()
                .stream()
                .map(SiteSessionDTO::new)
                .collect(Collectors.toList()), headers, HttpStatus.OK);
    }


}
