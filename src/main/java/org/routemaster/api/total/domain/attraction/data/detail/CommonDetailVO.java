package org.routemaster.api.total.domain.attraction.data.detail;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.routemaster.api.total.domain.attraction.data.search.AttractionVO;

import java.text.ParseException;
import java.util.Objects;

@Schema(
        name = "CommonDetailVO",
        description = "공통 상세 정보(제목, 연락처, 주소 좌표, 개요 정보 등)"
)
@Getter
public class CommonDetailVO extends AttractionVO {

    @Schema(
            description = "홈페이지",
            example = "경복궁 <a href=\"http://www.royalpalace.go.kr/\" target=\"_blank\" title=\"새창 : 경복궁 홈페이지로 이동\">http://www.royalpalace.go.kr</a><br />궁궐길라잡이 <a href=\"http://www.palaceguide.or.kr/\" target=\"_blank\" title=\"새창 : 궁궐길라잡이 홈페이지로 이동\">http://www.palaceguide.or.kr</a><br />한국의재발견 <a href=\"http://www.rekor.or.kr/\" target=\"_blank\" title=\"새창 : 한국의재발견 홈페이지로 이동\">http://www.rekor.or.kr</a><br />야간관람 예매<a href=\"https://ticket.11st.co.kr/Product/Detail?id=266194&prdNo=4239172482\"title=\"새창: 경복궁 야간관람 예매\"target=\"_blank\">https://ticket.11st.co.kr/</a>",
            nullable = true
    ) private @Getter String homepage;
    @Schema(
            description = "우편번호",
            example = "03045"
    ) private @Getter String zipcode;
    @Schema(
            description = "개요",
            example = "경복궁은 1395년 태조 이성계에 의해서 새로운 조선왕조의 법궁으로 지어졌다. 경복궁은 동궐(창덕궁)이나 서궐(경희궁)에 비해 위치가 북쪽에 있어 '북궐'이라 불리기도 했다."
    ) private @Getter String overview;

    public CommonDetailVO(JsonNode item) throws ParseException {
        super(item);
        this.homepage = item.get("homepage").asText().isEmpty() ? null : item.get("homepage").asText();
        this.zipcode = item.get("zipcode").asText().isEmpty() ? null : item.get("zipcode").asText();
        this.overview = item.get("overview").asText().isEmpty() ? null : item.get("overview").asText();
        if (Objects.equals(this.overview, "-")) {
            this.overview = null;
        }
    }

}
