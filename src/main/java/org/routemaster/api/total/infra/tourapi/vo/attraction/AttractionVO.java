package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class AttractionVO {

    @Schema(
            description = "제목",
            example = "경복궁"
    ) private @Getter String title;
    @Schema(
            description = "대분류",
            example = "A02"
    ) private @Getter String largeCategory;
    @Schema(
            description = "중분류",
            example = "A0208"
    ) private @Getter String mediumCategory;
    @Schema(
            description = "소분류",
            example = "A02080300"
    ) private @Getter String smallCategory;
    @Schema(
            description = "GPS x좌표",
            example = "126.976888"
    ) private @Getter Double mapX;
    @Schema(
            description = "GPS y좌표",
            example = "37.576264"
    ) private @Getter Double mapY;
    @Schema(
            description = "지역코드",
            example = "32",
            allowableValues = {
                    "1", "2", "3", "4", "5", "6", "7", "8", "31", "32"
            }
    ) private @Getter Integer areaCode;
    @Schema(
            description = "시군구코드",
            example = "13"
    ) private @Getter Integer sigunguCode;
    @Schema(
            description = "Map Level 응답",
            example = "6"
    ) private @Getter Integer mapLevel;
    @Schema(
            description = "주소",
            example = "서울특별시 종로구 사직로 161"
    ) private @Getter String address;
    @Schema(
            description = "상세 주소",
            example = "(서면) 부근"
    ) private @Getter String detailAddress;
    @Schema(
            description = "콘텐츠 ID",
            example = "126508"
    ) private @Getter Integer contentId;
    @Schema(
            description = "콘텐츠 타입 ID",
            example = "12"
    ) private @Getter Integer contentTypeId;
    @Schema(
            description = "저작권 유형(Type1:제1유형(출처표시-권장) Type3:제3유형(제1유형 + 변경금지))",
            example = "Type1",
            allowableValues = {
                    "Type1", "Type3"
            }
    ) private @Getter String copyrightType;
    @Schema(
            description = "대표 이미지(원본, 500*333) URI",
            example = "http://tong.visitkorea.or.kr/cms/resource/71/181971_image2_1.jpg"
    ) private @Getter URI image;
    @Schema(
            description = "대표 이미지(썸네일, 150*100) URI",
            example = "http://tong.visitkorea.or.kr/cms/resource/71/181971_image3_1.jpg"
    ) private @Getter URI thumbnailImage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(
            description = "콘텐츠 최초 등록일",
            example = "2022-06-24 09:00:00"
    ) private @Getter Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(
            description = "콘텐츠 수정일",
            example = "2022-06-24 09:00:00"
    ) private @Getter Date modifiedTime;
    @Schema(
            description = "교과서 속 여행지 여부",
            example = "false"
    ) private @Getter boolean bookTour;
    @Schema(
            description = "전화번호",
            example = "02-3700-3900"
    ) private @Getter String tel;

    public AttractionVO(JsonNode jsonNode) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        this.title = jsonNode.get("title").asText();
        this.largeCategory = jsonNode.get("cat1").asText();
        this.mediumCategory = jsonNode.get("cat2").asText();
        this.smallCategory = jsonNode.get("cat3").asText();
        this.mapX = jsonNode.get("mapx").asDouble();
        this.mapY = jsonNode.get("mapy").asDouble();
        this.areaCode = jsonNode.get("areacode").asInt();
        this.sigunguCode = jsonNode.get("sigungucode").asInt();
        this.mapLevel = jsonNode.get("mlevel").asInt();
        this.contentId = jsonNode.get("contentid").asInt();
        this.contentTypeId = jsonNode.get("contenttypeid").asInt();
        this.copyrightType = jsonNode.get("cpyrhtDivCd").asText().isEmpty() ? null : jsonNode.get("cpyrhtDivCd").asText();
        this.address = jsonNode.get("addr1").asText();
        this.detailAddress = jsonNode.get("addr2").asText().isEmpty() ? null : jsonNode.get("addr2").asText();
        this.image = jsonNode.get("firstimage").asText().isEmpty() ? null : URI.create(jsonNode.get("firstimage").asText());
        this.thumbnailImage = jsonNode.get("firstimage2").asText().isEmpty() ? null : URI.create(jsonNode.get("firstimage2").asText());
        this.createdTime = formatter.parse(jsonNode.get("createdtime").asText());
        this.modifiedTime = formatter.parse(jsonNode.get("modifiedtime").asText());
        this.tel = jsonNode.get("tel").asText().isEmpty() ? null : jsonNode.get("tel").asText();
        this.bookTour = jsonNode.get("booktour").asText() != "";
    }

}
