package ua.abond.social.security.acl;

public interface OwnedResource<T> {
    T getOwnerId();
}
