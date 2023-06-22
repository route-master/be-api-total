package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class ShoppingAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        private @Getter String babyCarriageInfo;
        private @Getter String creditcardInfo;
        private @Getter String allowPetInfo;
        private @Getter String cultureCenterInfo;
        private @Getter String fairDay;
        private @Getter String infoCenter;
        private @Getter String openDate;
        private @Getter String openTime;
        private @Getter String parking;
        private @Getter String restDate;
        private @Getter String restRoom;
        private @Getter String saleItem;
        private @Getter String saleItemCost;
        private @Getter String scale;
        private @Getter String shopGuide;
    }

    public static final class ShoppingAttractionDetailVOBuilder {
        public ShoppingAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.babyCarriageInfo = item.get("chkbabycarriageshopping").asText().isEmpty() ? null : item.get("chkbabycarriageshopping").asText();
                this.detail.creditcardInfo = item.get("chkcreditcardshopping").asText().isEmpty() ? null : item.get("chkcreditcardshopping").asText();
                this.detail.allowPetInfo = item.get("chkpetshopping").asText().isEmpty() ? null : item.get("chkpetshopping").asText();
                this.detail.cultureCenterInfo = item.get("culturecenter").asText().isEmpty() ? null : item.get("culturecenter").asText();
                this.detail.fairDay = item.get("fairday").asText().isEmpty() ? null : item.get("fairday").asText();
                this.detail.infoCenter = item.get("infocentershopping").asText().isEmpty() ? null : item.get("infocentershopping").asText();
                this.detail.openDate = item.get("opendateshopping").asText().isEmpty() ? null : item.get("opendateshopping").asText();
                this.detail.openTime = item.get("opentime").asText().isEmpty() ? null : item.get("opentime").asText();
                this.detail.parking = item.get("parkingshopping").asText().isEmpty() ? null : item.get("parkingshopping").asText();
                this.detail.restDate = item.get("restdateshopping").asText().isEmpty() ? null : item.get("restdateshopping").asText();
                this.detail.restRoom = item.get("restroom").asText().isEmpty() ? null : item.get("restroom").asText();
                this.detail.saleItem = item.get("saleitem").asText().isEmpty() ? null : item.get("saleitem").asText();
                this.detail.saleItemCost = item.get("saleitemcost").asText().isEmpty() ? null : item.get("saleitemcost").asText();
                this.detail.scale = item.get("scaleshopping").asText().isEmpty() ? null : item.get("scaleshopping").asText();
                this.detail.shopGuide = item.get("shopguide").asText().isEmpty() ? null : item.get("shopguide").asText();
            });
            return this;
        }
    }

}
