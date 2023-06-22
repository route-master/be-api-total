package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class LeportsAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        private @Getter String accomodationCount;
        private @Getter String babyCarriageInfo;
        private @Getter String creditcardInfo;
        private @Getter String allowPetInfo;
        private @Getter String experienceAgeRange;
        private @Getter String infoCenter;
        private @Getter String openPeriod;
        private @Getter String parking;
        private @Getter String parkingFee;
        private @Getter String reservation;
        private @Getter String restDate;
        private @Getter String scale;
        private @Getter String usageFee;
        private @Getter String hoursOfOperation;
    }

    public static final class LeportsAttractionDetailVOBuilder {
        public LeportsAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.accomodationCount = item.get("accomcountleports").asText().isEmpty() ? null : item.get("accomcountleports").asText();
                this.detail.babyCarriageInfo = item.get("chkbabycarriageleports").asText().isEmpty() ? null : item.get("chkbabycarriageleports").asText();
                this.detail.creditcardInfo = item.get("chkcreditcardleports").asText().isEmpty() ? null : item.get("chkcreditcardleports").asText();
                this.detail.allowPetInfo = item.get("chkpetleports").asText().isEmpty() ? null : item.get("chkpetleports").asText();
                this.detail.experienceAgeRange = item.get("expagerangeleports").asText().isEmpty() ? null : item.get("expagerangeleports").asText();
                this.detail.infoCenter = item.get("infocenterleports").asText().isEmpty() ? null : item.get("infocenterleports").asText();
                this.detail.openPeriod = item.get("openperiod").asText().isEmpty() ? null : item.get("openperiod").asText();
                this.detail.parking = item.get("parkingleports").asText().isEmpty() ? null : item.get("parkingleports").asText();
                this.detail.parkingFee = item.get("parkingfeeleports").asText().isEmpty() ? null : item.get("parkingfeeleports").asText();
                this.detail.reservation = item.get("reservation").asText().isEmpty() ? null : item.get("reservation").asText();
                this.detail.restDate = item.get("restdateleports").asText().isEmpty() ? null : item.get("restdateleports").asText();
                this.detail.scale = item.get("scaleleports").asText().isEmpty() ? null : item.get("scaleleports").asText();
                this.detail.usageFee = item.get("usefeeleports").asText().isEmpty() ? null : item.get("usefeeleports").asText();
                this.detail.hoursOfOperation = item.get("usetimeleports").asText().isEmpty() ? null : item.get("usetimeleports").asText();
            });
            return this;
        }
    }

}
