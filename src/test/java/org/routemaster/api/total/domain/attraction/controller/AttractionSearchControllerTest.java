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

    @Test
    public void testLocationBasedAttractionSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/search/location-based?numOfRows=10&pageNo=1&arrange=A&mapX=126.981611&mapY=37.568477&radius=20000&contentTypeId=14")
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
            assertTrue(attractionSearchVO.getTotalCount() > 0);
            assertNotNull(attractionSearchVO.getAttractions());
            attractionSearchVO.getAttractions().forEach(attractionVO -> {
                assertNotNull(attractionVO);
                assertNotNull(attractionVO.getContentId());
                assertEquals(14, attractionVO.getContentTypeId());
                // 실제로 반경 20km 이내에 있는지 확인
                assertTrue(Math.pow((126.981611 - attractionVO.getMapX()), 2)
                        + Math.pow((37.568477 - attractionVO.getMapY()), 2) <= Math.pow(20000, 2));
            });
        }).verifyComplete();
    }

    @Test
    public void testKeywordBasedAttractionSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/search/keyword-based?numOfRows=3&pageNo=2&keyword=크리스마스")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(AttractionSearchVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(3, attractionSearchVO.getNumOfRows());
            assertEquals(2, attractionSearchVO.getPageNo());
            assertTrue(attractionSearchVO.getTotalCount() > 0);
            assertNotNull(attractionSearchVO.getAttractions());
            attractionSearchVO.getAttractions().forEach(attractionVO -> {
                assertNotNull(attractionVO);
                assertNotNull(attractionVO.getContentId());
                assertTrue(attractionVO.getTitle().contains("크리스마스")); // 키워드가 포함되어 있는지 확인
            });
        }).verifyComplete();
    }

    @Test
    public void testFestivalSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/search/event?numOfRows=5&pageNo=2&eventStartDate=20231001")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(AttractionSearchVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(5, attractionSearchVO.getNumOfRows());
            assertEquals(2, attractionSearchVO.getPageNo());
            assertTrue(attractionSearchVO.getTotalCount() > 0);
            assertNotNull(attractionSearchVO.getAttractions());
            attractionSearchVO.getAttractions().forEach(attractionVO -> {
                assertNotNull(attractionVO);
                assertNotNull(attractionVO.getContentId());
                assertEquals(15, attractionVO.getContentTypeId());
            });
        }).verifyComplete();
    }

}
