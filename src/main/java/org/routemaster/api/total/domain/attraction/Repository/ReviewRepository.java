package org.routemaster.api.total.domain.attraction.Repository;

import org.routemaster.api.total.domain.attraction.DTO.ReviewDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewDTO, String> {
    List<ReviewDTO> findAllByContentId(String contentId);
}
