package ua.abond.social.config;

public class Constants {
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]+$";
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANG_KEY = "en";
    public static final String DATETIME_FORMAT = "yyyy-mm-dd hh:mm:ss";

    private Constants() { }
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER = "Bearer ";
}
