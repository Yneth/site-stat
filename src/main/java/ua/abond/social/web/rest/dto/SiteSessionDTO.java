package ua.abond.social.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ua.abond.social.config.Constants;
import ua.abond.social.domain.SiteSession;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class SiteSessionDTO {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATETIME_FORMAT)
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATETIME_FORMAT)
    private LocalDateTime endTime;
    private long duration;

    public SiteSessionDTO(SiteSession session) {
        setId(session.getId());
        setDuration(session.getDuration());
        setStartTime(session.getStartDateTime());
        setEndTime(session.getEndDateTime());
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
