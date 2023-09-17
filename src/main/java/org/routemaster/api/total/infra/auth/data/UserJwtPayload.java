package org.routemaster.api.total.infra.auth.data;

import java.util.Set;

public record UserJwtPayload(
    String baseUserId,
    String typeUserId,
    Set<String> authorities,
    UserType userType,
    JwtType jwtType
) {

    public enum UserType {
        EMAIL_USER,
        EMAIL_USER_READY,
        SOCIAL_USER,
    }

    public enum JwtType {

        ACCESS_TOKEN,
        REFRESH_TOKEN
    }
}
