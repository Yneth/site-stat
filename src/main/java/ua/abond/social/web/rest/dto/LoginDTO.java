package ua.abond.social.web.rest.dto;

import ua.abond.social.config.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginDTO {
    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 0, max = 50)
    private String username;

    @NotNull
    @Size(min = ManagedUserDTO.PASSWORD_MIN_LENGTH, max = ManagedUserDTO.PASSWORD_MAX_LENGTH)
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
