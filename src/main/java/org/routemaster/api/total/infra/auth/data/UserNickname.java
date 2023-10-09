package org.routemaster.api.total.infra.auth.data;

import java.util.List;

public record UserNickname(
    String id,
    String baseUserId,
    String nickname
) {

    public record UserNicknameList(
        List<UserNickname> nicknames
    ) {}
}
