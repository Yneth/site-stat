package ua.abond.social.domain;

public class SiteStatistic {
    private final Site site;
    private final Long usageDuration;

    public SiteStatistic(Site site, Long usageDuration) {
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
        if (!(o instanceof SiteStatistic)) return false;

        SiteStatistic that = (SiteStatistic) o;

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
