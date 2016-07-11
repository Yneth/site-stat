package ua.abond.social.web.rest.dto;

import org.hibernate.validator.constraints.URL;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;
import ua.abond.social.domain.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SiteDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @URL
    @NotNull
    private String url;

    private UserDTO user;

    private List<SiteSessionDTO> sessions = new ArrayList<>();

    public SiteDTO(Long id, String name, String url, User user) {
        setId(id);
        setName(name);
        setUrl(url);
        this.user = new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
                user.getEmail(), false, user.getLangKey(), null);
    }

    public SiteDTO(Long id, String name, String url, UserDTO user, List<SiteSessionDTO> sessions) {
        setId(id);
        setName(name);
        setUrl(url);
        setUser(user);
        setSessions(sessions);
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

    public List<SiteSessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SiteSessionDTO> sessions) {
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static SiteDTOBuilder from(Site site) {
        return new SiteDTOBuilder(site);
    }

    public static class SiteDTOBuilder {
        private Long id;
        private String name;
        private String url;
        private UserDTO user;
        private List<SiteSessionDTO> sessions = new ArrayList<>();

        private Site site;

        private SiteDTOBuilder(Site site) { this.site = site;}

        public SiteDTOBuilder withSessions() {
            this.sessions = site.getSiteSessions()
                    .stream()
                    .map(SiteSessionDTO::new)
                    .collect(Collectors.toList());
            return this;
        }

        public SiteDTOBuilder withId() {
            this.id = site.getId();
            return this;
        }

        public SiteDTOBuilder withUrl() {
            this.url = site.getUrl();
            return this;
        }

        public SiteDTOBuilder withName() {
            this.name = site.getName();
            return this;
        }

        public SiteDTOBuilder withUser() {
            this.user = new UserDTO(site.getUser());
            return this;
        }

        public SiteDTO exceptUser() {
            return withId().withSessions().withUrl().withName().build();
        }

        public SiteDTO exceptUserAndSessions() {
            return withId().withUrl().withName().build();
        }

        public SiteDTO build() {
            return new SiteDTO(id, name, url, user, sessions);
        }
    }
}
