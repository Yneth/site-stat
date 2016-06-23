package ua.abond.social.security.acl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ua.abond.social.security.acl.SecuredDomain;
import ua.abond.social.security.acl.SecuredDomainService;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleSecuredDomainService implements SecuredDomainService {
    private final Logger log = LoggerFactory.getLogger(SimpleSecuredDomainService.class);

    private final String domainPackage;

    private final Map<String, Class<?>> securedDomains;

    public SimpleSecuredDomainService(String domainPackage) throws ClassNotFoundException {
        this.domainPackage = domainPackage;
        this.securedDomains = initSecuredDomains();
    }

    @Override
    public SecuredDomain loadDomain(String name, Object domain) {
        if (!contains(name)) return null;
        SecuredDomain securedDomain = SecuredDomain.class.cast(domain);
        return securedDomain;
    }

    @Override
    public boolean contains(String name) {
        return securedDomains.containsKey(name);
    }

    private Map<String, Class<?>> initSecuredDomains() throws ClassNotFoundException {
        Map<String, Class<?>> securedDomains = Collections.EMPTY_MAP;
        if (!StringUtils.hasText(domainPackage)) {
            // TODO: throw exception and add log
            return securedDomains;
        }
        try {
            securedDomains = getClassesFromPackage(domainPackage)
                    .stream()
                    .filter(aClass -> SecuredDomain.class.isAssignableFrom(aClass))
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
