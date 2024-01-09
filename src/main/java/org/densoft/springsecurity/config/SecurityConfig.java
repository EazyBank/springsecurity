package org.densoft.springsecurity.config;

import org.densoft.springsecurity.filter.AuthoritiesLoggingAfterFilter;
import org.densoft.springsecurity.filter.CsrfCookieFilter;
import org.densoft.springsecurity.filter.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

//@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    private final CsrfCookieFilter csrfCookieFilter;
    private final RequestValidationFilter requestValidationFilter;
    private final AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter;

    public SecurityConfig(CsrfCookieFilter csrfCookieFilter, RequestValidationFilter requestValidationFilter, AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter) {
        this.csrfCookieFilter = csrfCookieFilter;
        this.requestValidationFilter = requestValidationFilter;
        this.authoritiesLoggingAfterFilter = authoritiesLoggingAfterFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> registry

                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myLoans").hasRole("USER")
                .requestMatchers("/myCards").hasRole("USER")

                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices", "/contact", "/register").permitAll()
        );
        http.cors(Customizer.withDefaults()); // by default, uses a Bean by the name of corsConfigurationSource

        http.securityContext(httpSecuritySecurityContextConfigurer -> httpSecuritySecurityContextConfigurer.requireExplicitSave(false));
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        // csrf token config
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
                .csrfTokenRequestHandler(requestAttributeHandler)
                .ignoringRequestMatchers("/contact", "/register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        );

        http.addFilterBefore(requestValidationFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(authoritiesLoggingAfterFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(csrfCookieFilter, BasicAuthenticationFilter.class);

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
