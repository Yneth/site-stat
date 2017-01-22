package ua.abond.social.web.rest.dto;

import ua.abond.social.domain.Site;

import java.util.Objects;

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
        if (this == o)
            return true;
        if (!(o instanceof SiteSummary))
            return false;
        SiteSummary that = (SiteSummary) o;
        return Objects.equals(getSite(), that.getSite()) &&
                Objects.equals(getUsageDuration(), that.getUsageDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSite(), getUsageDuration());
    }
}
