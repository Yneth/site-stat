package ua.abond.social.web.rest.dto;

import ua.abond.social.domain.SiteSession;

public class ManagedSiteSessionDTO extends SiteSessionDTO {
    private Long siteId;

    public ManagedSiteSessionDTO(SiteSession session) {
        super(session);
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}
