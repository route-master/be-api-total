package org.routemaster.api.total.domain.plan.data;

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

    @Id
    private String id;

    @Indexed
    @Field(name = "writer")
    private String writer;
    @Field(name = "name")
    private String name;
    @Field(name = "description")
    private String description;
    @Field(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Indexed
    @Field(name = "participants")
    private Set<String> participants;

    @Indexed
    @Field(name = "begin_date")
    private LocalDateTime beginDate;

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
