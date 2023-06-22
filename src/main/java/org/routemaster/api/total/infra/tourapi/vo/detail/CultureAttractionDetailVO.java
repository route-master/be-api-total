package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class CultureAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        private @Getter Integer accomodationCount;
        private @Getter String babyCarriageInfo;
        private @Getter String creditcardInfo;
        private @Getter String allowPetInfo;
        private @Getter String discountInfo;
        private @Getter String infoCenter;
        private @Getter String parking;
        private @Getter String parkingFee;
        private @Getter String restDate;
        private @Getter String usageFee;
        private @Getter String hoursOfOperation;
        private @Getter String scale;
        private @Getter String spendTime;
    }

    public final static class CultureAttractionDetailVOBuilder {

        public CultureAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.accomodationCount = item.get("accomcountculture").asText().isEmpty() ? null : item.get("accomcountculture").asInt();
                this.detail.babyCarriageInfo = item.get("chkbabycarriageculture").asText().isEmpty() ? null : item.get("chkbabycarriageculture").asText();
                this.detail.creditcardInfo = item.get("chkcreditcardculture").asText().isEmpty() ? null : item.get("chkcreditcardculture").asText();
                this.detail.allowPetInfo = item.get("chkpetculture").asText().isEmpty() ? null : item.get("chkpetculture").asText();
                this.detail.discountInfo = item.get("discountinfo").asText().isEmpty() ? null : item.get("discountinfo").asText();
                this.detail.infoCenter = item.get("infocenterculture").asText().isEmpty() ? null : item.get("infocenterculture").asText();
                this.detail.parking = item.get("parkingculture").asText().isEmpty() ? null : item.get("parkingculture").asText();
                this.detail.parkingFee = item.get("parkingfee").asText().isEmpty() ? null : item.get("parkingfee").asText();
                this.detail.restDate = item.get("restdateculture").asText().isEmpty() ? null : item.get("restdateculture").asText();
                this.detail.usageFee = item.get("usefee").asText().isEmpty() ? null : item.get("usefee").asText();
                this.detail.hoursOfOperation = item.get("usetimeculture").asText().isEmpty() ? null : item.get("usetimeculture").asText();
                this.detail.scale = item.get("scale").asText().isEmpty() ? null : item.get("scale").asText();
                this.detail.spendTime = item.get("spendtime").asText().isEmpty() ? null : item.get("spendtime").asText();
            });
            return this;
        }

    }
}

