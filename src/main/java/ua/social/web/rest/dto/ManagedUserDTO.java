package ua.social.web.rest.dto;

import ua.social.domain.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class ManagedUserDTO extends UserDTO {
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    private Long id;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserDTO() {
    }

    public ManagedUserDTO(User user) {
        super(user);
        this.id = user.getId();
        this.password = null;
    }

    public ManagedUserDTO(Long id, String login, String password, String firstName, String lastName,
                          String email, boolean activated, String langKey, Set<String> authorities) {
        super(login, firstName, lastName, email, activated, langKey, authorities);
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
