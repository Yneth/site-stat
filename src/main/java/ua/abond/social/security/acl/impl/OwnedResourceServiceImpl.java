package ua.abond.social.security.acl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ua.abond.social.security.acl.OwnedResource;
import ua.abond.social.security.acl.OwnedResourceService;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OwnedResourceServiceImpl implements OwnedResourceService {
    private final Logger log = LoggerFactory.getLogger(OwnedResourceServiceImpl.class);

    private final String domainPackage;

    private final Map<String, Class<?>> securedDomains;

    public OwnedResourceServiceImpl(String domainPackage) throws ClassNotFoundException {
        this.domainPackage = domainPackage;
        this.securedDomains = initSecuredDomains();
    }

    @Override
    public OwnedResource loadDomain(String name, Object domain) {
        if (!contains(name)) return null;

        return OwnedResource.class.cast(domain);
    }

    @Override
    public boolean contains(String name) {
        return securedDomains.containsKey(name);
    }

    private Map<String, Class<?>> initSecuredDomains() throws ClassNotFoundException {

        if (!StringUtils.hasText(domainPackage)) {
            String message = "Could not find domainPackage with path " + domainPackage;
            log.debug(message);
            throw new MissingDomainPackageException(message);
        }
        Map<String, Class<?>> securedDomains;
        try {
            securedDomains = getClassesFromPackage(domainPackage)
                    .stream()
                    .filter(aClass -> OwnedResource.class.isAssignableFrom(aClass))
                    .collect(Collectors.toMap(Class::getSimpleName, Function.identity()));
        } catch (ClassNotFoundException e) {
            log.error("No such class found.", e);
            throw e;
        }
        return securedDomains;
    }


    private List<Class<?>> getClassesFromPackage(String packageName) throws ClassNotFoundException {
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));

        List<File> files = Arrays.asList(new File(root.getFile()).listFiles((dir, name) -> {
            return name.endsWith(".class");
        }));
        List<Class<?>> classes = new ArrayList<>();
        for (File file : files) {
            String className = file.getName().replace(".class", "");
            classes.add(Class.forName(packageName + "." + className));
        }
        return classes;
    }
}
