package ua.abond.social.security.acl.impl;

public class MissingDomainPackageException extends RuntimeException {
    public MissingDomainPackageException() {
        super();
    }

    public MissingDomainPackageException(String message) {
        super(message);
    }

    public MissingDomainPackageException(String message, Throwable cause) {
        super(message, cause);
    }
}
