package ua.abond.social.security.acl;

public interface OwnedResourceService {
    OwnedResource loadDomain(String name, Object domain);
    boolean contains(String name);
}
