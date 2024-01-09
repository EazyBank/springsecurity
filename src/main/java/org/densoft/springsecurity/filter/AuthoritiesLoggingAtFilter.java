package org.densoft.springsecurity.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AuthoritiesLoggingAtFilter implements Filter {

    private final Logger LOG =
            Logger.getLogger(AuthoritiesLoggingAtFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("Authentication Validation is in progress");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
