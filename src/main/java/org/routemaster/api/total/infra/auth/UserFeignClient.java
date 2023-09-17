package org.routemaster.api.total.infra.auth;

import java.util.List;
import org.routemaster.api.total.infra.auth.data.BaseUser.UserInfo;
import org.routemaster.api.total.infra.auth.data.UserNickname;
import org.routemaster.api.total.infra.auth.data.UserNickname.UserNicknameList;
import org.routemaster.api.total.infra.auth.data.UserProfile.UserProfileList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserFeignClient", url = "http://auth.route-master.org//v1")
public interface UserFeignClient {

    @GetMapping("/user/info/profile/{baseUserId}")
    UserInfo userInfo(
        @PathVariable String baseUserId,
        @RequestHeader(name = "Authorization") String accessToken
    );
    @GetMapping("/user/info/profile/list")
    UserProfileList userProfileList(
        @RequestParam List<String> baseUserIds,
        @RequestHeader(name = "Authorization") String accessToken
    );
    @GetMapping("/user/info/profile/me")
    UserInfo myUserInfo(
        @RequestHeader(name = "Authorization") String accessToken
    );

    @GetMapping("/user/info/profile/nickname/{baseUserId}")
    UserNickname userNickname(
        @PathVariable String baseUserId,
        @RequestHeader(name = "Authorization") String accessToken
    );

    @GetMapping("/user/info/profile/nickname/list")
    UserNicknameList userNicknameList(
        @RequestParam List<String> baseUserIds,
        @RequestHeader(name = "Authorization") String accessToken
    );
}
