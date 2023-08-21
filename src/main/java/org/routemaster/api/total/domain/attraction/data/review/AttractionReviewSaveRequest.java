package org.routemaster.api.total.domain.attraction.data.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AttractionReviewSaveRequest {

    private String id;
    private String userId;
    private String contentId;
    @Length(min = 1)
    private String reviewComment;
    private String imageUrl;
    private Integer rating;


}
