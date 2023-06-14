package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class AttractionVO {

    private @Getter String title;
    private @Getter String largeCategory;
    private @Getter String mediumCategory;
    private @Getter String smallCategory;
    private @Getter Double mapX;
    private @Getter Double mapY;
    private @Getter Integer areaCode;
    private @Getter Integer sigunguCode;
    private @Getter Double dist;
    private @Getter Integer mapLevel;
    private @Getter String address;
    private @Getter String detailAddress;
    private @Getter Integer contentId;
    private @Getter Integer contentTypeId;
    private @Getter String copyrightType;
    private @Getter URI image;
    private @Getter URI thumbnailImage;
    private @Getter Date createdTime;
    private @Getter Date modifiedTime;
    private @Getter boolean bookTour;
    private @Getter String tel;

    public static final class AttractionVOBuilder {

        SimpleDateFormat formatter =new SimpleDateFormat("yyyyMMddHHmmss");
        public AttractionVOBuilder fromJsonNode(JsonNode jsonNode) throws ParseException {
            this.title = jsonNode.get("title").asText();
            this.largeCategory = jsonNode.get("cat1").asText();
            this.mediumCategory = jsonNode.get("cat2").asText();
            this.smallCategory = jsonNode.get("cat3").asText();
            this.mapX = jsonNode.get("mapx").asDouble();
            this.mapY = jsonNode.get("mapy").asDouble();
            this.areaCode = jsonNode.get("areacode").asInt();
            this.sigunguCode = jsonNode.get("sigungucode").asInt();
            this.dist = jsonNode.get("dist").asDouble();
            this.mapLevel = jsonNode.get("mlevel").asInt();
            this.address = jsonNode.get("addr1").asText();
            this.detailAddress = jsonNode.get("addr2").asText().isEmpty() ? null : jsonNode.get("addr2").asText();
            this.image = jsonNode.get("firstimage").asText().isEmpty() ? null : URI.create(jsonNode.get("firstimage").asText());
            this.thumbnailImage = jsonNode.get("firstimage2").asText().isEmpty() ? null : URI.create(jsonNode.get("firstimage2").asText());
            this.createdTime = formatter.parse(jsonNode.get("createdtime").asText());
            this.modifiedTime = formatter.parse(jsonNode.get("modifiedtime").asText());
            this.tel = jsonNode.get("tel").asText().isEmpty() ? null : jsonNode.get("tel").asText();
            this.bookTour = jsonNode.get("booktour").asText() != "";
            return this;
        }
    }

}
