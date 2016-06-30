package ua.abond.social.web.rest.dto;

import org.hibernate.validator.constraints.URL;
import ua.abond.social.domain.SocialNetwork;
import ua.abond.social.domain.SocialNetworkSession;
import ua.abond.social.domain.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @URL
    @NotNull
    private String url;

    private UserDTO user;

    private List<SocialNetworkSession> sessions = new ArrayList<>();

    public SocialNetworkDTO(Long id, String name, String url, User user) {
        setName(name);
        setUrl(url);
        this.user = new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getActivated(), user.getLangKey(), null);
    }

    public SocialNetworkDTO() {
    }

    public SocialNetworkDTO(SocialNetwork sc) {
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

    public List<SocialNetworkSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<SocialNetworkSession> sessions) {
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
