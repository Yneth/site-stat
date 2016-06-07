package ua.social.domain;

public enum Role {
    ROLE_ANONYMOUS,
    ROLE_BASIC,
    ROLE_OAUTH2,
    ROLE_ADMIN,
    ROLE_SYSTEM,
    IS_AUTHENTICATED_REMEMBERED; //Transitory role
}
