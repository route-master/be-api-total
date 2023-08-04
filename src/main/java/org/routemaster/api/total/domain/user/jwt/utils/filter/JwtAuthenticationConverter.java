package org.routemaster.api.total.domain.user.jwt.utils.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.user.jwt.data.UserJwtPayload;
import org.routemaster.api.total.domain.user.jwt.service.UserJwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

    private final UserJwtService userJwtService;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        // Extract JWT token from the request, validate it, and create an Authentication object
        // Example: Parse token from Authorization header
        String token = extractTokenFromHeader(exchange.getRequest().getHeaders().getFirst("Authorization"));
        if (token == null || token.isEmpty()) {
            return Mono.empty();
        }

        // Validate token and retrieve user details
        if (isValidToken(token)) {
            return getUserDetailsFromToken(token)
                .map(userJwtPayload -> {
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    for (String auth : userJwtPayload.getAuthorities()) {
                        authorities.add(new SimpleGrantedAuthority(auth));
                    }
                    return UsernamePasswordAuthenticationToken.authenticated(
                        userJwtPayload.getBaseUserId(),
                        userJwtPayload.getTypeUserId(),
                        authorities);
                });
        } else {
            return Mono.empty(); // Invalid token
        }
    }

    private String extractTokenFromHeader(String header) {
        // Extract and return the token from the "Bearer <token>" format
        if (header == null || header.isEmpty()) {
            return header;
        }
        return header.substring(7);
    }

    private boolean isValidToken(String token) {
        // Validate token using your token validation logic
        return userJwtService.validateToken(token);
    }

    private Mono<UserJwtPayload> getUserDetailsFromToken(String token) {
        // Retrieve user details from the token
        UserJwtPayload userJwtPayload = userJwtService.getPayload(token);
        return Mono.just(userJwtPayload);
    }
}
