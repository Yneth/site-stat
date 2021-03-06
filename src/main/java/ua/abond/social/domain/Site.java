package ua.abond.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.abond.social.security.acl.OwnedResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "site")
@SequenceGenerator(name = "seq", sequenceName = "site_id_seq", allocationSize = 1)
public class Site extends AbstractEntity implements OwnedResource<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private User user;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "url", nullable = false)
    private String url;

    // TODO: move cascade remove to db script and remove siteSessions from Site domain
    @JsonIgnore
    @OneToMany(mappedBy = "site", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<SiteSession> siteSessions = new ArrayList<>();

    public Site() {
    }

    public Site(Long id) {
        setId(id);
    }

    public Site(Long id, String name, String url) {
        setId(id);
        setName(name);
        setUrl(url);
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
}
