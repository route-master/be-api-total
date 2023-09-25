package org.routemaster.api.total.infra.auth.data;

import java.time.LocalDateTime;

public record UserAccess(
    String id,
    String baseUserId,
    Boolean birthDate,
    Boolean profileImageUrl,
    String createdAt,
    String updatedAt
) {}
