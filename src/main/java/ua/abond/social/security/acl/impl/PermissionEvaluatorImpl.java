package ua.abond.social.security.acl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.abond.social.security.acl.SecuredDomain;
import ua.abond.social.security.acl.SecuredDomainService;

import java.io.Serializable;
import java.util.Optional;

@Component("permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator {
    private final Logger log = LoggerFactory.getLogger(PermissionEvaluatorImpl.class);

    private final SecuredDomainService securedDomainService;

    @Autowired
    public PermissionEvaluatorImpl(SecuredDomainService securedDomainService) {
        this.securedDomainService = securedDomainService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !(permission instanceof String)) {
            log.debug("Unauthorized access or wrong permission type.");
            return false;
        }

//         Todo: change it with interceptors
        if (targetDomainObject instanceof Optional) {
            targetDomainObject = ((Optional) targetDomainObject).get();
        }

//         Todo: change to ObjectMapper and also add interceptors
        String securedDomainName = targetDomainObject.getClass().getSimpleName();
//        if (targetDomainObject.getClass().isAnnotationPresent(DTO.class)) {
//            securedDomainName = securedDomainName.replace("DTO", "");
//        }

        SecuredDomain domain = securedDomainService.loadDomain(securedDomainName, targetDomainObject);
        if (domain == null) {
            log.debug("Domain is not registered as SecuredDomain Object {}", targetDomainObject);
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal == null || !(principal instanceof Owner)) {
            log.debug("Principal Object {} does not inherit Owner interface.", principal);
            return false;
        }

        Owner owner = (Owner) principal;

        if (!owner.getId().equals(domain.getOwnerId())) {
            log.debug("Owner {} does not own a SecuredDomain {}", owner, domain);
            return false;
        }

        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }
}
