package ua.social.domain;

import javax.persistence.*;

@Entity
@Table(name = "SOCIAL_NETWORK")
@SequenceGenerator(name = "SEQ", sequenceName = "SOCIAL_NETWORK_SOCIAL_NETWORK_ID_SEQ",
        allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "SOCIAL_NETWORK_ID"))
public class SocialNetwork extends AbstractEntity {
    @ManyToOne
    private User user;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "URL", nullable = false)
    private String url;

    public SocialNetwork() {
    }

    public SocialNetwork(long id, User user, String name, String url) {
        this(user, name, url);
        this.id = id;
    }

    public SocialNetwork(User user, String name, String url) {
        this.user = user;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
