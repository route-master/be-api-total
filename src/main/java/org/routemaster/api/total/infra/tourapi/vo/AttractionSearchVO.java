package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.routemaster.api.total.infra.amadeus.vo.FlightOfferSearchVO;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class AttractionSearchVO {

    private @Getter List<AttractionVO> attractions;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;

    public static final class AttractionSearchVOBuilder {
        public AttractionSearchVOBuilder fromJsonNode(JsonNode jsonNode) {
            this.attractions = new ArrayList<>();
            jsonNode.get("items").get("item").forEach(item -> {
                try {
                    AttractionVO attractionVO = AttractionVO.builder().fromJsonNode(item).build();
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
