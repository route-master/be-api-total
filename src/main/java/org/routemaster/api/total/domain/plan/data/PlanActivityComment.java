package org.routemaster.api.total.domain.plan.data;


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

    @Id
    private String id;
    @Indexed
    @Field(name = "plan_activity_id")
    private String planActivityId;
    @Field(name = "writer")
    private String writer;
    @Field(name = "description")
    private String description;
}
