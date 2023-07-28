package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class ShoppingAttractionDetailVO {

    @Schema(
            description = "API 호출 결과의 상태 코드",
            example = "0000"
    ) private @Getter String resultCode;
    @Schema(
            description = "API 호출 결과의 상태 메시지",
            example = "OK"
    ) private @Getter String resultMessage;
    @Schema(
            description = "한 페이지 당 결과 수",
            example = "10"
    ) private @Getter Integer numOfRows;
    @Schema(
            description = "페이지 번호",
            example = "1"
    ) private @Getter Integer pageNo;
    @Schema(
            description = "전체 결과 수",
            example = "100"
    ) private @Getter Integer totalCount;
    @Schema(
            description = "쇼핑 상세 정보"
    ) private @Getter ShoppingAttractionDetail detail;

    private static class ShoppingAttractionDetail {
        @Schema(
                description = "유모차 대여 정보",
                example = "없음"
        ) private @Getter String babyCarriageInfo;
        @Schema(
                description = "신용카드 가능 정보",
                example = "없음"
        ) private @Getter String creditcardInfo;
        @Schema(
                description = "애완동물 동반 가능 정보",
                example = "불가"
        ) private @Getter String allowPetInfo;
        @Schema(
                description = "문화센터 바로가기"
        ) private @Getter String cultureCenterInfo;
        @Schema(
                description = "장 서는 날",
                example = "월~일요일"
        ) private @Getter String fairDay;
        @Schema(
                description = "문의 및 안내",
                example = "02-3213-2258"
        ) private @Getter String infoCenter;
        @Schema(
                description = "개장일"
        ) private @Getter String openDate;
        @Schema(
                description = "영업 시간",
                example = "10:30~20:00"
        ) private @Getter String openTime;
        @Schema(
                description = "주차 시설",
                example = "주차 가능"
        ) private @Getter String parking;
        @Schema(
                description = "쉬는 날",
                example = "매주 월요일"
        ) private @Getter String restDate;
        @Schema(
                description = "화장실 설명",
                example = "완비"
        ) private @Getter String restRoom;
        @Schema(
                description = "판매 품목",
                example = "시계"
        ) private @Getter String saleItem;
        @Schema(
                description = "판매 품목별 가격"
        ) private @Getter String saleItemCost;
        @Schema(
                description = "규모"
        ) private @Getter String scale;
        @Schema(
                description = "매장 안내",
                example = "이용환급창구 운영사업자 : 글로벌텍스프리(GTF), 환급서비스 제공방식 : 사후"
        ) private @Getter String shopGuide;
    }

    public static final class ShoppingAttractionDetailVOBuilder {
        public ShoppingAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new ShoppingAttractionDetail();
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
