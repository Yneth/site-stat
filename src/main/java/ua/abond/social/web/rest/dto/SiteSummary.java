package ua.abond.social.web.rest.dto;

import ua.abond.social.domain.Site;

public class SiteSummary {
    private final Site site;
    private final Long usageDuration;

    public SiteSummary(Site site, Long usageDuration) {
        this.site = site;
        this.usageDuration = usageDuration;
    }

    public Site getSite() {
        return site;
    }

    public Long getUsageDuration() {
        return usageDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SiteSummary)) return false;

        SiteSummary that = (SiteSummary) o;

        if (getSite() != null ? !getSite().equals(that.getSite()) : that.getSite() != null) return false;
        return getUsageDuration() != null ? getUsageDuration().equals(that.getUsageDuration()) : that.getUsageDuration() == null;

    }

    @Override
    public int hashCode() {
        int result = getSite() != null ? getSite().hashCode() : 0;
        result = 31 * result + (getUsageDuration() != null ? getUsageDuration().hashCode() : 0);
        return result;
    }
}
