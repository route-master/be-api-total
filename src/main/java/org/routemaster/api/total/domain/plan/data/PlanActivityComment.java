package org.routemaster.api.total.domain.plan.data;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants.Exclude;
import org.routemaster.api.total.global.data.BaseDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "plan_activity_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PlanActivityComment extends BaseDocument {

    @Schema(description = "플랜 액티비티 댓글 ID")
    @Id
    private String id;
    @Schema(description = "플랜 액티비티 ID")
    @Indexed
    @Field(name = "plan_activity_id")
    private String planActivityId;
    @Schema(description = "플랜 액티비티 댓글 작성자")
    @Field(name = "writer")
    private String writer;
    @Schema(description = "플랜 액티비티 댓글 내용")
    @Field(name = "description")
    private String description;
}
