package ua.social.security.acl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ua.social.security.acl.SecuredDomain;
import ua.social.security.acl.SecuredDomainService;

import java.io.Serializable;

public class PermissionEvaluatorImpl implements PermissionEvaluator {
    private final Logger log = LoggerFactory.getLogger(PermissionEvaluatorImpl.class);

    private final SecuredDomainService securedDomainService;

    @Autowired
    public PermissionEvaluatorImpl(SecuredDomainService securedDomainService) {
        this.securedDomainService = securedDomainService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !(permission instanceof String)) return false;

        String securedDomainName = targetDomainObject.getClass().getSimpleName();
        SecuredDomain domain = securedDomainService.loadDomain(securedDomainName, targetDomainObject);
        if (domain == null) return false;

        Object principal = authentication.getPrincipal();
        if (principal == null || !(principal instanceof Owner)) return false;

        Owner owner = (Owner) principal;

        if (!owner.getId().equals(domain.getOwnerId())) return false;

        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // TODO: implement
        return false;
    }
}
