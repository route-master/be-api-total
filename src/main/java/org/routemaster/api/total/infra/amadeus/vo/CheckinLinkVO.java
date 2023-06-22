package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.CheckinLink;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class CheckinLinkVO {

    private String type;
    private String id;
    private String href;
    private String channel;

    public static final class CheckinLinkVOBuilder {
        public CheckinLinkVOBuilder checkInLink(CheckinLink checkinLink) {
            this.type = checkinLink.getType();
            this.id = checkinLink.getType();
            this.href = checkinLink.getHref();
            this.channel = checkinLink.getChannel();
            return this;
        }
    }

}
