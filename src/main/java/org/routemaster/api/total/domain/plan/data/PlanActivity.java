package org.routemaster.api.total.domain.plan.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.total.domain.plan.data.subdata.PlanMapInfo;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentInfo;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentLog;
import org.routemaster.api.total.global.data.BaseDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "plan_activity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PlanActivity extends BaseDocument {

    @Schema(description = "플랜 액티비티 ID")
    @Id
    private String id;
    @Schema(description = "플랜 그룹 ID")
    @Indexed
    @Field(name = "plan_group_id")
    private String planGroupId;
    @Schema(description = "플랜 액티비티 생성자")
    @Field(name = "writer")
    private String writer;
    @Schema(description = "플랜 액티비티 이름")
    @Field(name = "name")
    private String name;
    @Schema(description = "플랜 액티비티 설명")
    @Field(name = "description")
    private String description;
    @Schema(description = "플랜 액티비티 썸네일 이미지")
    @Field(name = "thumbnail_image_url")
    private String thumbnailImageUrl;
    @Schema(description = "플랜 액티비티 시작 시간")
    @Field(name = "begin_date")
    private LocalDateTime beginDate;
    @Schema(description = "플랜 액티비티 종료 시간")
    @Field(name = "end_date")
    private LocalDateTime endDate;
    @Schema(description = "플랜 액티비티 맵 정보")
    @Field(name = "map_info")
    private PlanMapInfo mapInfo;
    @Schema(description = "플랜 액티비티 결제 정보")
    @Field(name = "planPaymentLog")
    private PlanPaymentInfo paymentInfo;
    @Schema(description = "플랜 액티비티 타입(HOTEL, RESTAURANT, ACTIVITY)")
    @Field(name = "activityType")
    private ActivityType activityType;
    @Schema(description = "플랜 액티비티 참조 타입(TOUR_API, KAKAO_MAP)")
    @Field(name = "referenceType")
    private ReferenceType referenceType;
    @Schema(description = "플랜 액티비티 참조 ID")
    @Field(name = "referenceId")
    private String referenceId;

    public void setPlanPaymentInfo(PlanPaymentInfo planPaymentInfo) {
        this.paymentInfo = planPaymentInfo;
    }

    public enum ActivityType {
        HOTEL,
        RESTAURANT,
        ACTIVITY,
    }

    public enum ReferenceType {
        TOUR_API,
        KAKAO_MAP,
    }
}