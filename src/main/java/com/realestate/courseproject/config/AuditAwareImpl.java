package com.realestate.courseproject.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl") //name of this bean will be mentioned in the main class
public class AuditAwareImpl implements AuditorAware<String> {

    //Getting the name of current user. If there is no authenticated user, method return null
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
