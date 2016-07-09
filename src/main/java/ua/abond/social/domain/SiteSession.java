package ua.abond.social.domain;

import ua.abond.social.security.acl.OwnedResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;

@Entity
@Table(name = "social_network_session")
@SequenceGenerator(name = "seq", sequenceName = "social_network_session_id_seq", allocationSize = 1)
public class SiteSession extends AbstractEntity implements OwnedResource<Long> {
    // In minutes
    @Column(name = "duration")
    private Long duration;
    @NotNull
    @Column(name = "time_start", nullable = false)
    private ZonedDateTime start;
    @NotNull
    @Column(name = "time_end", nullable = false)
    private ZonedDateTime end;
    @ManyToOne
    private Site site;

    public SiteSession() {
    }

    private Long calculateDuration() {
        return Optional.ofNullable(start).flatMap(s -> {
            return Optional.ofNullable(end).map(e -> {
                return Duration.between(start, end).toMinutes();
            });
        }).orElse(0L);
    }

    @Override
    public Long getOwnerId() {
        return site.getOwnerId();
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
        this.duration = calculateDuration();
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
        this.duration = calculateDuration();
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SiteSession)) return false;

        SiteSession that = (SiteSession) o;

        if (getId() != that.getId()) return false;
        if (getSite() != null ? !getSite().equals(that.getSite()) : that.getSite() != null)
            return false;
        if (getStart() != null ? !getStart().equals(that.getStart()) : that.getStart() != null) return false;
        if (getEnd() != null ? !getEnd().equals(that.getEnd()) : that.getEnd() != null) return false;
        return getDuration() != null ? getDuration().equals(that.getDuration()) : that.getDuration() == null;

    }

    @Override
    public int hashCode() {
        int result = getSite() != null ? getSite().hashCode() : 0;
        result = 31 * result + Long.hashCode(getId());
        result = 31 * result + (getStart() != null ? getStart().hashCode() : 0);
        result = 31 * result + (getEnd() != null ? getEnd().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SiteSession{" +
                "id=" + getId() +
                "site=" + site +
                ", start=" + start +
                ", end=" + end +
                ", duration=" + duration +
                '}';
    }
}
