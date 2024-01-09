package org.densoft.springsecurity.filter;

import jakarta.servlet.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import static java.text.MessageFormat.format;

@Component
public class AuthoritiesLoggingAfterFilter implements Filter {

    private final Logger LOG =
            Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).ifPresent(authentication -> LOG.info(format("User {0} is successfully authenticated and has the authorities {1}", authentication.getName(), authentication.getAuthorities().toString())));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
