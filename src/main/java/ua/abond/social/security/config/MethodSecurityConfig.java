package ua.abond.social.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import ua.abond.social.security.acl.OwnedResourceService;
import ua.abond.social.security.acl.impl.OwnedResourceServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private static final String DOMAIN_PACKAGE = "ua.abond.social.domain";

    @Autowired
    private PermissionEvaluator permissionEvaluator;

    @Bean(name = "expressionHandler")
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        return handler;
    }

    @Bean
    public OwnedResourceService ownedResourceService() throws ClassNotFoundException {
        return new OwnedResourceServiceImpl(DOMAIN_PACKAGE);
    }
}
