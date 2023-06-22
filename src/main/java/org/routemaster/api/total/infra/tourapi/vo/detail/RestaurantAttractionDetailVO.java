package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class RestaurantAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        public @Getter String creditcardInfo;
        public @Getter String discountInfo;
        public @Getter String signatureMenu;
        public @Getter String infoCenter;
        public @Getter String kidsFacility;
        public @Getter String openDate;
        public @Getter String packing;
        public @Getter String parking;
        public @Getter String reservation;
        public @Getter String restDate;
        public @Getter String scale;
        public @Getter String smoking;
        public @Getter String menu;
        public @Getter String licenseNumber;
    }

    public static final class RestaurantAttractionDetailVOBuilder {
        public RestaurantAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.creditcardInfo = item.get("chkcreditcardfood").asText().isEmpty() ? null : item.get("chkcreditcardfood").asText();
                this.detail.discountInfo = item.get("discountinfofood").asText().isEmpty() ? null : item.get("discountinfofood").asText();
                this.detail.signatureMenu = item.get("firstmenu").asText().isEmpty() ? null : item.get("firstmenu").asText();
                this.detail.infoCenter = item.get("infocenterfood").asText().isEmpty() ? null : item.get("infocenterfood").asText();
                this.detail.kidsFacility = item.get("kidsfacility").asText().isEmpty() ? null : item.get("kidsfacility").asText();
                this.detail.openDate = item.get("opendatefood").asText().isEmpty() ? null : item.get("opendatefood").asText();
                this.detail.packing = item.get("packing").asText().isEmpty() ? null : item.get("packing").asText();
                this.detail.parking = item.get("parkingfood").asText().isEmpty() ? null : item.get("parkingfood").asText();
                this.detail.reservation = item.get("reservationfood").asText().isEmpty() ? null : item.get("reservationfood").asText();
                this.detail.restDate = item.get("restdatefood").asText().isEmpty() ? null : item.get("restdatefood").asText();
                this.detail.scale = item.get("scalefood").asText().isEmpty() ? null : item.get("scalefood").asText();
                this.detail.smoking = item.get("smoking").asText().isEmpty() ? null : item.get("smoking").asText();
                this.detail.menu = item.get("treatmenu").asText().isEmpty() ? null : item.get("treatmenu").asText();
                this.detail.licenseNumber = item.get("lcnsno").asText().isEmpty() ? null : item.get("lcnsno").asText();
            });
            return this;
        }
    }

}
