package ua.abond.social.security.acl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.abond.social.security.acl.OwnedResource;
import ua.abond.social.security.acl.OwnedResourceService;
import ua.abond.social.security.acl.Owner;

import java.io.Serializable;
import java.util.Optional;

@Component("permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator {
    private final Logger log = LoggerFactory.getLogger(PermissionEvaluatorImpl.class);

    private final OwnedResourceService ownedResourceService;

    @Autowired
    public PermissionEvaluatorImpl(OwnedResourceService ownedResourceService) {
        this.ownedResourceService = ownedResourceService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !(permission instanceof String)) {
            log.debug("Unauthorized access or wrong permission type.");
            return false;
        }
        Object target = targetDomainObject;
//         Todo: change it with interceptors
        if (targetDomainObject instanceof Optional) {
            target = ((Optional) targetDomainObject).orElse(null);
            if (target == null) {
                log.debug("Target domain object is null");
                return false;
            }
        }

//         Todo: change to ObjectMapper and also add interceptors
        String securedDomainName = target.getClass().getSimpleName();
//        if (targetDomainObject.getClass().isAnnotationPresent(DTO.class)) {
//            securedDomainName = securedDomainName.replace("DTO", "");
//        }

        OwnedResource domain = ownedResourceService.loadDomain(securedDomainName, target);
        if (domain == null) {
            log.debug("Domain is not registered as OwnedResource Object {}", targetDomainObject);
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal == null || !(principal instanceof Owner)) {
            log.debug("Principal Object {} does not inherit Owner interface.", principal);
            return false;
        }

        Owner owner = (Owner) principal;

        if (!owner.getOwnerId().equals(domain.getOwnerId())) {
            log.debug("Owner {} does not own a OwnedResource {}", owner, domain);
            return false;
        }

        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }
}
