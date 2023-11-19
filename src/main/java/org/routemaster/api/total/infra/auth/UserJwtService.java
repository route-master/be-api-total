package org.routemaster.api.total.infra.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.infra.auth.data.UserJwtPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserJwtService {

    @Value("${jwt.secret}")
    private String secret;

    private final JwtService jwtService;

    public UserJwtPayload getPayload(String token) {
        final GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(UserJwtPayload.class, new UserJwtPayload.Deserializer());
        final Gson gson = gb.create();
        String payload = jwtService.getPayload(token, secret);
        return gson.fromJson(payload, UserJwtPayload.class);
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token, secret);
    }
}
