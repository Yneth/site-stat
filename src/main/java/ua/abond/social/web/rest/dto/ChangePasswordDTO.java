package ua.abond.social.web.rest.dto;

import ua.abond.social.config.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordDTO {
    @NotNull(message = "password.change.empty")
    @Size(
            message = "password.change.length",
            min = Constants.PASSWORD_MIN_LENGTH,
            max = Constants.PASSWORD_MAX_LENGTH
    )
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
