package org.routemaster.api.total.global.config;

import org.routemaster.api.total.domain.user.jwt.utils.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.WebFilter;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class GlobalSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .build();
    }

    @Bean
    public WebFilter jwtAuthenticationFilter(ReactiveAuthenticationManager authenticationManager,
        ServerAuthenticationConverter authenticationConverter) {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager, authenticationConverter);
        filter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));
        return filter;
    }
}
