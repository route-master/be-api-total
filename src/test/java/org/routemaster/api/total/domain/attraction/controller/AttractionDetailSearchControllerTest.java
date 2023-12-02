package org.routemaster.api.total.domain.attraction.controller;

import org.junit.jupiter.api.Test;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.service.AttractionDetailSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttractionDetailSearchControllerTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private AttractionDetailSearchService service;

    @Test
    public void testCommonDetailSearch() {
        Integer testContentId = 126508;
        StepVerifier.create(
                client.get().uri("/attraction/detail/common?contentId=" + testContentId)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(AttractionSearchVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(1, attractionSearchVO.getNumOfRows());
            assertEquals(1, attractionSearchVO.getPageNo());
            assertEquals(1, attractionSearchVO.getTotalCount());
            attractionSearchVO.getAttractions().forEach(detail -> {
                assertNotNull(detail);
                assertEquals(testContentId, detail.getContentId());
            });
        }).verifyComplete();
    }

}
