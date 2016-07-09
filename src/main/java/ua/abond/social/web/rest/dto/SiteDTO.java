package ua.abond.social.web.rest.dto;

import org.hibernate.validator.constraints.URL;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.domain.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SiteDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @URL
    @NotNull
    private String url;

    private UserDTO user;

    private List<SiteSession> sessions = new ArrayList<>();

    public SiteDTO(Long id, String name, String url, User user) {
        setName(name);
        setUrl(url);
        this.user = new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getActivated(), user.getLangKey(), null);
    }

    public SiteDTO() {
    }

    public SiteDTO(Site sc) {
        this(sc.getId(), sc.getName(), sc.getUrl(), sc.getUser());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<SiteSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<SiteSession> sessions) {
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
