package ua.social.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object to return as body in JWT Authentication.
 */
public class JwtToken {

    private String idToken;

    public JwtToken(String idToken) {
        this.idToken = idToken;
    }

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
