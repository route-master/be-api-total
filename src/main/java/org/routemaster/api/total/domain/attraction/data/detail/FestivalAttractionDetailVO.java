package org.routemaster.api.total.domain.attraction.data.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@Slf4j
public class FestivalAttractionDetailVO {

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
    private @Getter FestivalAttractionDetail detail;

    private static class FestivalAttractionDetail {
        @Schema(
                description = "관람 가능 연령",
                example = "제한없음"
        ) private @Getter String ageLimit;
        @Schema(
                description = "예매처"
        ) private @Getter String bookingPlace;
        @Schema(
                description = "할인 정보"
        ) private @Getter String discountInfo;
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @Schema(
                description = "행사 시작일",
                example = "2022-09-30"
        ) private @Getter Date eventStartDate;
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @Schema(
                description = "행사 종료일",
                example = "2022-10-08"
        ) private @Getter Date eventEndDate;
        @Schema(
                description = "행사 홈페이지"
        ) private @Getter String eventHomepage;
        @Schema(
                description = "행사 장소",
                example = "코엑스일대, 신사동 가로수길, 일원동 마루공원, 강남역"
        ) private @Getter String eventPlace;
        @Schema(
                description = "축제 등급"
        ) private @Getter String festivalGrade;
        @Schema(
                description = "행사장 위치 안내",
                example = "수원화성일원"
        ) private @Getter String placeInfo;
        @Schema(
                description = "공연 시간",
                example = "연중(밤10시이후제한)"
        ) private @Getter String playTime;
        @Schema(
                description = "행사 프로그램",
                example = ""
        ) private @Getter String program;
        @Schema(
                description = "관람 소요시간",
                example = ""
        ) private @Getter String spendTime;
        @Schema(
                description = "주최자 정보",
                example = "수원문화재단"
        ) private @Getter String sponsor;
        @Schema(
                description = "주최자 연락처",
                example = "031-290-3563"
        ) private @Getter String sponsorTel;
        @Schema(
                description = "주관사 정보",
                example = "경기관광공사,수원문 화재단"
        ) private @Getter String organizer;
        @Schema(
                description = "주관사 연락처",
                example = "031-290-3563"
        ) private @Getter String organizerTel;
        @Schema(
                description = "부대 행사"
        ) private @Getter String subEvent;
        @Schema(
                description = "이용 요금",
                example = "7,500원"
        ) private @Getter String usageFee;
    }

    public final static class FestivalAttractionDetailVOBuilder {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        public FestivalAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new FestivalAttractionDetail();
             jsonNode.get("items").get("item").forEach(item -> {
                 this.detail.bookingPlace = item.get("bookingplace").asText().isEmpty() ? null : item.get("bookingplace").asText();
                 this.detail.discountInfo = item.get("discountinfofestival").asText().isEmpty() ? null : item.get("discountinfofestival").asText();
                 try {
                     this.detail.eventStartDate = item.get("eventstartdate").asText().isEmpty() ? null : formatter.parse(item.get("eventstartdate").asText());
                     this.detail.eventEndDate = item.get("eventenddate").asText().isEmpty() ? null : formatter.parse(item.get("eventenddate").asText());
                 } catch (ParseException e) {
                     throw new RuntimeException(e);
                 }
                 this.detail.ageLimit = item.get("agelimit").asText().isEmpty() ? null : item.get("agelimit").asText();
                 this.detail.eventHomepage = item.get("eventhomepage").asText().isEmpty() ? null : item.get("eventhomepage").asText();
                 this.detail.eventPlace = item.get("eventplace").asText().isEmpty() ? null : item.get("eventplace").asText();
                 this.detail.festivalGrade = item.get("festivalgrade").asText().isEmpty() ? null : item.get("festivalgrade").asText();
                 this.detail.placeInfo = item.get("placeinfo").asText().isEmpty() ? null : item.get("placeinfo").asText();
                 this.detail.program = item.get("program").asText().isEmpty() ? null : item.get("program").asText();
                 this.detail.playTime = item.get("playtime").asText().isEmpty() ? null : item.get("playtime").asText();
                 this.detail.spendTime = item.get("spendtimefestival").asText().isEmpty() ? null : item.get("spendtimefestival").asText();
                 this.detail.sponsor = item.get("sponsor1").asText().isEmpty() ? null : item.get("sponsor1").asText();
                 this.detail.sponsorTel = item.get("sponsor1tel").asText().isEmpty() ? null : item.get("sponsor1tel").asText();
                 this.detail.organizer = item.get("sponsor2").asText().isEmpty() ? null : item.get("sponsor2").asText();
                 this.detail.organizerTel = item.get("sponsor2tel").asText().isEmpty() ? null : item.get("sponsor2tel").asText();
                 this.detail.subEvent = item.get("subevent").asText().isEmpty() ? null : item.get("subevent").asText();
                 this.detail.usageFee = item.get("usetimefestival").asText().isEmpty() ? null : item.get("usetimefestival").asText();
             });
            return this;
        }
    }

}
