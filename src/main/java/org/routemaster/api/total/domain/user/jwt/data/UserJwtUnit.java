package org.routemaster.api.total.domain.user.jwt.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.total.global.domain.jwt.impl.vo.JwtUnit;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserJwtUnit {

    private JwtUnit accessToken;
    private JwtUnit refreshToken;
}
