package org.routemaster.api.total.domain.attraction.data.review;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "attraction_review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
public class AttractionReview {

    @Id
    private String id;
    @Field(name = "user_id")
    private String userId;
    @Field(name = "content_id")
    private String contentId;
    @Field(name = "review_comment")
    private String reviewComment;
    @Field(name = "rating")
    private Integer rating;
    @Field(name = "image_url")
    private String imageUrl;

}
