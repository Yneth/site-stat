package ua.abond.social.domain;

import ua.abond.social.security.acl.OwnedResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


// TODO: rename to Site
@Entity
@Table(name = "social_network")
@SequenceGenerator(name = "seq", sequenceName = "social_network_id_seq", allocationSize = 1)
public class Site extends AbstractEntity implements OwnedResource<Long> {

    @ManyToOne
    private User user;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "url", nullable = false)
    private String url;
    @OneToMany(mappedBy = "id")
    private List<SiteSession> siteSessions = new ArrayList<>();

    public Site() {
    }

    @Override
    public Long getOwnerId() {
        return user.getId();
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

    public List<SiteSession> getSiteSessions() {
        return siteSessions;
    }

    public void setSiteSessions(List<SiteSession> siteSessions) {
        this.siteSessions = siteSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;

        Site that = (Site) o;

        if (getId() != that.getId()) return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + Long.hashCode(getId());
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + getId() +
                "user=" + user +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", siteSessions=" + siteSessions +
                '}';
    }
}
