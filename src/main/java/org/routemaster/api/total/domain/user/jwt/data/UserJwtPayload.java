package org.routemaster.api.total.domain.user.jwt.data;

import java.util.Collection;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.routemaster.api.total.domain.user.jwt.utils.constant.JwtType;
import org.routemaster.api.total.domain.user.jwt.utils.constant.UserType;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class UserJwtPayload {

    private String baseUserId;
    private String typeUserId;
    private Set<String> authorities;
    private UserType userType;
    private JwtType jwtType;
}
