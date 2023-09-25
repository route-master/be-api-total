package org.routemaster.api.total.infra.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.infra.auth.data.BaseUser;
import org.routemaster.api.total.infra.auth.data.BaseUser.UserInfo;
import org.routemaster.api.total.infra.auth.data.UserJwtPayload;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    public static final String BASE_USER_KEY = "BASE_USER";

    private final AuthenticationManager authenticationManager;
    private final UserJwtService userJwtService;
    private final UserFeignClient userFeignClient;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Mono.empty();
        }
        String authToken = authHeader.substring(7);
        Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
        UserJwtPayload payload = userJwtService.getPayload(authToken);
        UserInfo userInfo = userFeignClient.myUserInfo(authHeader);
        exchange.getAttributes().put(BASE_USER_KEY, BaseUser.builder()
                .accessToken(authToken)
                .payload(payload)
                .userInfo(userInfo)
            .build());
        return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
    }
}
