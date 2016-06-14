package ua.social.web.rest.dto;

public class LoginDTO {
    private String username;
    private String password;
    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }
}
