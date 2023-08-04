package org.routemaster.api.total.global.domain.jwt.impl.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class JwtUnit {

    private String token;
    private Long expiresIn;
}

