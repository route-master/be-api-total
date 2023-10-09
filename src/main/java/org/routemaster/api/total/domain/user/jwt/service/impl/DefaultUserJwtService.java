package org.routemaster.api.total.domain.user.jwt.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.user.jwt.data.UserJwtPayload;
import org.routemaster.api.total.domain.user.jwt.service.UserJwtService;
import org.routemaster.api.total.global.domain.jwt.impl.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserJwtService implements UserJwtService {

    private final JwtService jwtService;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public UserJwtPayload getPayload(String token) {
        String payload = jwtService.getPayload(token, secret);
        return new Gson().fromJson(payload, UserJwtPayload.class);
    }

    @Override
    public Boolean validateToken(String token) {
        return jwtService.validateToken(token, secret);
    }
}
