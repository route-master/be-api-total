package org.routemaster.api.total.infra.auth.data;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Profile;

public record UserProfile(
    String id,
    String baseUserId,
    String nickname,
    String birthDate,
    String profileImageUrl,
    String createdAt,
    String updatedAt,
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
