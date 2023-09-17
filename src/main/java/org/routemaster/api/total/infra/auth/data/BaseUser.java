package org.routemaster.api.total.infra.auth.data;

import lombok.Builder;

@Builder
public record BaseUser(
    String accessToken,
    UserJwtPayload payload,
    UserInfo userInfo
) {
    public record UserInfo(
        UserProfile profile,
        UserAccess access
    ) {}
}
