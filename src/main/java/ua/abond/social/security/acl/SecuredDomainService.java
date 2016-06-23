package ua.abond.social.security.acl;

public interface SecuredDomainService {
    SecuredDomain loadDomain(String name, Object domain);
    boolean contains(String name);
}
