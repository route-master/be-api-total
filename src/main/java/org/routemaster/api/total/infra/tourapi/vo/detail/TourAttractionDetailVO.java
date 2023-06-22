package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class TourAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;


    private static class Detail {
        private @Getter Integer contentId;
        private @Getter Integer contentTypeId;
        private @Getter Integer accomodationCount;
        private @Getter String babyCarriageInfo;
        private @Getter String creditcardInfo;
        private @Getter String allowPetInfo;
        private @Getter String experienceAgeRange;
        private @Getter String experienceGuide;
        private @Getter boolean cultureHeritage;
        private @Getter boolean NatureHeritage;
        private @Getter boolean recordHeritage;
        private @Getter String infoCenter;
        private @Getter String openDate;
        private @Getter String parking;
        private @Getter String restDate;
        private @Getter String seasonsOfOperation;
        private @Getter String hoursOfOperation;
    }

    public final static class TourAttractionDetailVOBuilder {

        public TourAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                    this.detail.contentId = item.get("contentid").asInt();
                    this.detail.contentTypeId = item.get("contenttypeid").asInt();
                    this.detail.accomodationCount = item.get("accomcount").asText().isEmpty() ? null : item.get("accomCount").asInt();
                    this.detail.babyCarriageInfo = item.get("chkbabycarriage").asText().isEmpty() ? null : item.get("chkbabycarriage").asText();
                    this.detail.creditcardInfo = item.get("chkcreditcard").asText().isEmpty() ? null : item.get("chkcreditcard").asText();
                    this.detail.allowPetInfo = item.get("chkpet").asText().isEmpty() ? null : item.get("chkpet").asText();
                    this.detail.experienceAgeRange = item.get("expguide").asText().isEmpty() ? null : item.get("expguide").asText();
                    this.detail.experienceGuide = item.get("expguide").asText().isEmpty() ? null : item.get("expguide").asText();
                    this.detail.cultureHeritage = item.get("heritage1").asText().equals("1");
                    this.detail.NatureHeritage = item.get("heritage2").asText().equals("1");
                    this.detail.recordHeritage = item.get("heritage3").asText().equals("1");
                    this.detail.infoCenter = item.get("infocenter").asText().isEmpty() ? null : item.get("infocenter").asText();
                    this.detail.openDate = item.get("opendate").asText().isEmpty() ? null : item.get("opendate").asText();
                    this.detail.parking = item.get("parking").asText().isEmpty() ? null : item.get("parking").asText();
                    this.detail.restDate = item.get("restdate").asText().isEmpty() ? null : item.get("restdate").asText();
                    this.detail.seasonsOfOperation = item.get("useseason").asText().isEmpty() ? null : item.get("useseason").asText();
                    this.detail.hoursOfOperation = item.get("usetime").asText().isEmpty() ? null : item.get("usetime").asText();
            });
            return this;
        }

    }

}
