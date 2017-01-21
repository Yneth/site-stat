package ua.abond.social.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ua.abond.social.config.Constants;
import ua.abond.social.domain.SiteSession;

import java.time.LocalDateTime;

public class SiteSessionDTO {
    private Long id;
    private Long siteId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd\'T\'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd\'T\'HH:mm:ss")
    private LocalDateTime endTime;
    private long duration;

    public SiteSessionDTO() {
    }

    public SiteSessionDTO(SiteSession session) {
        setId(session.getId());
        setDuration(session.getDuration());
        setStartTime(session.getStartDateTime());
        setEndTime(session.getEndDateTime());
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
