package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class AttractionSearchVO {

    @Schema(
            description = "API 호출 결과의 상태 코드",
            example = "0000"
    ) private @Getter String resultCode;
    @Schema(
            description = "API 호출 결과의 상태 메시지",
            example = "OK"
    ) private @Getter String resultMessage;
    @Schema(
            description = "Attraction 결과 리스트(Local/Area/Keyword 기반 검색 결과에 따라 결과값이 일부 다르므로 API 직접 호출하여 확인 필요)"
    )
    private @Getter List<AttractionVO> attractions;
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

    public static final class AttractionSearchVOBuilder {
        public AttractionSearchVOBuilder locationBasedItems(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = new LocationBasedAttractionVO(item);
                    this.attractions.add(attractionVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            this.numOfRows = jsonNode.get("numOfRows").asInt();
            this.pageNo = jsonNode.get("pageNo").asInt();
            this.totalCount = jsonNode.get("totalCount").asInt();
            return this;
        }

        public AttractionSearchVOBuilder areaBasedItems(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = new AreaBasedAttractionVO(item);
                    this.attractions.add(attractionVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            this.numOfRows = jsonNode.get("numOfRows").asInt();
            this.pageNo = jsonNode.get("pageNo").asInt();
            this.totalCount = jsonNode.get("totalCount").asInt();
            return this;
        }

        public AttractionSearchVOBuilder keywordBasedItems(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = new KeywordBasedAttractionVO(item);
                    this.attractions.add(attractionVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            this.numOfRows = jsonNode.get("numOfRows").asInt();
            this.pageNo = jsonNode.get("pageNo").asInt();
            this.totalCount = jsonNode.get("totalCount").asInt();
            return this;
        }

        public AttractionSearchVOBuilder festivalItems(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = new FestivalAttractionVO(item);
                    this.attractions.add(attractionVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            this.numOfRows = jsonNode.get("numOfRows").asInt();
            this.pageNo = jsonNode.get("pageNo").asInt();
            this.totalCount = jsonNode.get("totalCount").asInt();
            return this;
        }

        public AttractionSearchVOBuilder stayItems(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = new StayAttractionVO(item);
                    this.attractions.add(attractionVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            this.numOfRows = jsonNode.get("numOfRows").asInt();
            this.pageNo = jsonNode.get("pageNo").asInt();
            this.totalCount = jsonNode.get("totalCount").asInt();
            return this;
        }

        public AttractionSearchVOBuilder commonDetailItems(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = new CommonDetailVO(item);
                    this.attractions.add(attractionVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            this.numOfRows = jsonNode.get("numOfRows").asInt();
            this.pageNo = jsonNode.get("pageNo").asInt();
            this.totalCount = jsonNode.get("totalCount").asInt();
            return this;
        }
    }

}
