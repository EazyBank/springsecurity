package org.densoft.springsecurity.config;

import org.densoft.springsecurity.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final CsrfCookieFilter csrfCookieFilter;

    public SecurityConfig(CsrfCookieFilter csrfCookieFilter) {
        this.csrfCookieFilter = csrfCookieFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConvertor());

        http.authorizeHttpRequests(registry -> registry
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myLoans").authenticated()
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices", "/contact", "/register").permitAll()
        );

        http.cors(Customizer.withDefaults()); // by default, uses a Bean by the name of corsConfigurationSource

        // do not generate JSESSIONIDS and http sessions
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // csrf token config
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
                .csrfTokenRequestHandler(requestAttributeHandler)
                .ignoringRequestMatchers("/contact", "/register", "/login")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        );

        http.addFilterAfter(csrfCookieFilter, BasicAuthenticationFilter.class);

        //        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.exceptionHandling(configurer -> configurer.accessDeniedHandler((request, response, accessDeniedException) -> {
            System.out.println("access denied handler");
            System.out.println(accessDeniedException.getMessage());
            accessDeniedException.printStackTrace();
        }).authenticationEntryPoint((request, response, authException) -> {
            System.out.println("authentication entry point");
            System.out.println(authException.getMessage());
            authException.printStackTrace();
        }));

        http.oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                httpSecurityOAuth2ResourceServerConfigurer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)).accessDeniedHandler((request, response, accessDeniedException) -> {
                    System.out.println("access denied handler2");
                    System.out.println(accessDeniedException.getMessage());
                    accessDeniedException.printStackTrace();
                }).authenticationEntryPoint((request, response, authException) -> {
                    System.out.println("authentication entry point2");
                    System.out.println(authException.getMessage());
                    authException.printStackTrace();
                }));
        return http.build();
    }
}
