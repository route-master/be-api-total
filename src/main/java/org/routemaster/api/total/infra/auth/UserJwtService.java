package org.routemaster.api.total.infra.auth;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.infra.auth.data.UserJwtPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJwtService {

    @Value("${jwt.secret}")
    private String secret;

    private final JwtService jwtService;

    public UserJwtPayload getPayload(String token) {
        String payload = jwtService.getPayload(token, secret);
        return new Gson().fromJson(payload, UserJwtPayload.class);
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token, secret);
    }
}
