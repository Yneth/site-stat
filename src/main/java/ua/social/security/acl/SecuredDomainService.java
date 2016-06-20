package ua.social.security.acl;

public interface SecuredDomainService {
    SecuredDomain loadDomain(String name, Object domain);
    boolean contains(String name);
}
