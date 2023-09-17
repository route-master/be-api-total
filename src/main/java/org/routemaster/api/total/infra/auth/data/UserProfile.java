package org.routemaster.api.total.infra.auth.data;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Profile;

public record UserProfile(
    String id,
    String baseUserId,
    String nickname,
    LocalDateTime birthDate,
    String profileImageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    AccessType accessType
) {
    public enum AccessType {

        ALL,
        FOLLOWERS_ONLY,
        RESTRICTED
    }

    public record UserProfileList(
        List<Profile> profiles
    ) {}
}
