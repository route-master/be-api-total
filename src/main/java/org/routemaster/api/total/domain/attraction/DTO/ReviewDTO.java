package org.routemaster.api.total.domain.attraction.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Document(collection = "review")
public class ReviewDTO {
    private String id;
    private String contentId;
    private String title;
    private String review;
    private String reviewImage;
    private String userId;
    private String userName;
    private Integer rating;
    private Date createdDate;
}
