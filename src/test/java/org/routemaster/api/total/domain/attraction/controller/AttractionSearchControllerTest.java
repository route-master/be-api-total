package org.routemaster.api.total.domain.attraction.controller;

import org.junit.jupiter.api.Test;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttractionSearchControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private AttractionSearchService service;

    @Test
    public void testAreaBasedAttractionSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/search/area-based?numOfRows=10&pageNo=1&arrange=A&contentTypeId=12&areaCode=1&sigunguCode=1")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(AttractionSearchVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(10, attractionSearchVO.getNumOfRows());
            assertEquals(1, attractionSearchVO.getPageNo());
            assertEquals(43, attractionSearchVO.getTotalCount());
            attractionSearchVO.getAttractions().forEach(attractionVO -> {
                assertNotNull(attractionVO);
                assertEquals(12, attractionVO.getContentTypeId());
                assertEquals(1, attractionVO.getAreaCode());
                assertEquals(1, attractionVO.getSigunguCode());
            });
        }).verifyComplete();
    }

}
