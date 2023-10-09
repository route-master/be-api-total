package org.routemaster.api.total.domain.user.jwt.service;

import org.routemaster.api.total.domain.user.jwt.data.UserJwtPayload;

public interface UserJwtService {

    UserJwtPayload getPayload(String token);
    Boolean validateToken(String token);
}