package org.routemaster.api.total.domain.plan.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.total.global.data.BaseDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "plan_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PlanGroup extends BaseDocument {

    @Schema(description = "그룹 ID")
    @Id
    private String id;

    @Schema(description = "그룹 생성자")
    @Indexed
    @Field(name = "writer")
    private String writer;
    @Schema(description = "그룹 이름")
    @Field(name = "name")
    private String name;
    @Schema(description = "그룹 설명")
    @Field(name = "description")
    private String description;
    @Schema(description = "그룹 썸네일 이미지")
    @Field(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Schema(description = "여행 플랜 그룹 참여자")
    @Indexed
    @Field(name = "participants")
    private Set<String> participants;

    @Schema(description = "여행 시작일")
    @Indexed
    @Field(name = "begin_date")
    private LocalDateTime beginDate;

    @Schema(description = "여행 종료일")
    @Indexed
    @Field(name = "end_date")
    private LocalDateTime endDate;

    public void invite(String username) {
        this.participants.add(username);
    }

    public void exit(String username) {
        this.participants.remove(username);
    }
}
