package org.routemaster.api.total.infra.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String getPayload(String token, String secret) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public Boolean validateToken(String token, String secret) {
        Jws<Claims> claimsJws = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token);

        return !claimsJws.getBody()
            .getExpiration()
            .before(new Date());
    }
}
