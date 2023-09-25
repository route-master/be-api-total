package org.routemaster.api.total.infra.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.infra.auth.data.UserJwtPayload;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final UserJwtService userJwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        UserJwtPayload payload = userJwtService.getPayload(token);
        log.info("payload: {}", payload);
        return Mono.just(userJwtService.validateToken(token))
            .filter(valid -> valid)
            .map(valid -> new UsernamePasswordAuthenticationToken(
                payload.baseUserId(),
                null,
                payload.authorities().stream().map(SimpleGrantedAuthority::new).toList()
            ));
    }
}
