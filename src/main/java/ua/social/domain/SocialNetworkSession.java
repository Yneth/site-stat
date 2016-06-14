package ua.social.domain;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@Entity
@Table(name = "social_network_session")
@SequenceGenerator(name = "seq", sequenceName = "social_network_session_id_seq", allocationSize = 1)
public class SocialNetworkSession extends AbstractEntity {
    @ManyToOne
    private SocialNetwork socialNetwork;

    @Column(name = "TIME_START", nullable = false)
    private LocalTime start;
    @Column(name = "TIME_END")
    private LocalTime end;

    // In minutes
    @Column(name="INTERVAL")
    private long interval;

    public SocialNetworkSession() {
    }

    public SocialNetworkSession(long id, SocialNetwork socialNetwork, LocalTime start, LocalTime end, long interval) {
        this(socialNetwork, start, end, interval);
        this.id = id;
    }

    public SocialNetworkSession(SocialNetwork socialNetwork, LocalTime start, LocalTime end, long interval) {
        this.socialNetwork = socialNetwork;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    private long calculateMinutes() {
        return Duration.between(
                Optional.of(start).orElse(LocalTime.MIN),
                Optional.of(end).orElse(LocalTime.MIN)
        ).toMinutes();
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
        this.interval = calculateMinutes();
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
        this.interval = calculateMinutes();
    }

    public long getInterval() {
        return interval;
    }
}
