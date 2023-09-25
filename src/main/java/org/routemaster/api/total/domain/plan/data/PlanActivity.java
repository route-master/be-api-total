package org.routemaster.api.total.domain.plan.data;

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

    @Id
    private String id;
    @Indexed
    @Field(name = "plan_group_id")
    private String planGroupId;

    @Field(name = "writer")
    private String writer;
    @Field(name = "name")
    private String name;
    @Field(name = "description")
    private String description;
    @Field(name = "thumbnail_image_url")
    private String thumbnailImageUrl;
    @Field(name = "begin_date")
    private LocalDateTime beginDate;
    @Field(name = "end_date")
    private LocalDateTime endDate;
    @Field(name = "map_info")
    private PlanMapInfo mapInfo;
    @Field(name = "planPaymentLog")
    private PlanPaymentInfo paymentInfo;
    @Field(name = "activityType")
    private ActivityType activityType;
    @Field(name = "referenceType")
    private ReferenceType referenceType;
    @Field(name = "referenceId")
    private String referenceId;

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