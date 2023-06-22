package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class FestivalAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        private @Getter String ageLimit;
        private @Getter String bookingPlace;
        private @Getter String discountInfo;
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private @Getter Date eventStartDate;
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private @Getter Date eventEndDate;
        private @Getter String eventHomepage;
        private @Getter String eventPlace;
        private @Getter String festivalGrade;
        private @Getter String placeInfo;
        private @Getter String playTime;
        private @Getter String program;
        private @Getter String spendTime;
        private @Getter String sponsor;
        private @Getter String sponsorTel;
        private @Getter String organizer;
        private @Getter String organizerTel;
        private @Getter String subEvent;
        private @Getter String usageFee;
    }

    public final static class FestivalAttractionDetailVOBuilder {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        public FestivalAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.ageLimit = item.get("agelimit").asText().isEmpty() ? null : item.get("agelimit").asText();
                this.detail.bookingPlace = item.get("bookingplace").asText().isEmpty() ? null : item.get("bookingplace").asText();
                this.detail.discountInfo = item.get("discountinfofestival").asText().isEmpty() ? null : item.get("discountinfofestival").asText();
                try {
                    this.detail.eventStartDate = item.get("eventstartdate").asText().isEmpty() ? null : formatter.parse(item.get("eventstartdate").asText());;
                    this.detail.eventEndDate = item.get("eventenddate").asText().isEmpty() ? null : formatter.parse(item.get("eventenddate").asText());;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.detail.eventHomepage = item.get("eventhomepage").asText().isEmpty() ? null : item.get("eventhomepage").asText();
                this.detail.eventPlace = item.get("eventplace").asText().isEmpty() ? null : item.get("eventplace").asText();
                this.detail.festivalGrade = item.get("festivalgrade").asText().isEmpty() ? null : item.get("festivalgrade").asText();
                this.detail.placeInfo = item.get("placeinfo").asText().isEmpty() ? null : item.get("placeinfo").asText();
                this.detail.playTime = item.get("playtime").asText().isEmpty() ? null : item.get("playtime").asText();
                this.detail.program = item.get("program").asText().isEmpty() ? null : item.get("program").asText();
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
