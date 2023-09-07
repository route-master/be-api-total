package org.routemaster.api.total.domain.recommend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "area_code_mapping_kma_knto")
@Getter
public class AreaCodeMapping {

    @Field(name="kma_region_code")
    private String kmaRegionCode;
    @Field(name="area_name")
    private String areaName;
    @Field(name="sigungu_name")
    private String sigunguName;
    @Field(name="dong_name")
    private String dongName;
    @Field(name="longitude")
    private Double logitude;
    @Field(name="latitude")
    private Double latitude;
    @Field(name="knto_area_code")
    private Integer kntoAreaCode;
    @Field(name="knto_sigungu_code")
    private Integer kntoSigunguCode;

}

