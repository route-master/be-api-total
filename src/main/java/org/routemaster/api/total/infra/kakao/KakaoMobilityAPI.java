package org.routemaster.api.total.infra.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KakaoMobilityAPI {

    public static String baseUrl;
    public static String authorizationKey;

    @Value("${kakao.rest-api.base-url}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Value("${kakao.rest-api.authorization-key}")
    public void setAuthorizationKey(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

}
