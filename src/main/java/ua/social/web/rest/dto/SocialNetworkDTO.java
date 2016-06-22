package ua.social.web.rest.dto;

import org.hibernate.validator.constraints.URL;
import ua.social.domain.SocialNetwork;
import ua.social.domain.User;

import javax.validation.constraints.NotNull;

public class SocialNetworkDTO {

    @NotNull
    private String name;
    @URL
    @NotNull
    private String url;

    private UserDTO user;

    public SocialNetworkDTO(String name, String url, User user) {
        setName(name);
        setUrl(url);
        if (user != null)
            setUser(new UserDTO(user));
    }

    public SocialNetworkDTO(SocialNetwork sc) {
        this(sc.getName(), sc.getUrl(), sc.getUser());
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
}
