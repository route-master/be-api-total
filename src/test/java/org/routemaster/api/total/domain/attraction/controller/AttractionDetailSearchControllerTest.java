package org.routemaster.api.total.domain.attraction.controller;

import org.junit.jupiter.api.Test;
import org.routemaster.api.total.domain.attraction.data.detail.*;
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

    @Test
    public void testTourAttractionDetailSearch() {
        Integer testContentId = 1949905;
        StepVerifier.create(
                client.get().uri("/attraction/detail/tour?contentId=" + testContentId)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(TourAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionSearchVO -> {
            assertNotNull(attractionSearchVO);
            assertEquals("0000", attractionSearchVO.getResultCode());
            assertEquals("OK", attractionSearchVO.getResultMessage());
            assertEquals(1, attractionSearchVO.getNumOfRows());
            assertEquals(1, attractionSearchVO.getPageNo());
            assertEquals(1, attractionSearchVO.getTotalCount());
            assertNotNull(attractionSearchVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testCultureAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/culture?contentId=2714751")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(CultureAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testStayAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/stay?contentId=142785")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(StayAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testFestivalAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/festival?contentId=2992932")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(FestivalAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testLeportsAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/leports?contentId=131139")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(LeportsAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testRestaurantAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/restaurant?contentId=2869760")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(RestaurantAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testShoppingAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/shopping?contentId=2924134")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(ShoppingAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

    @Test
    public void testCourseAttractionDetailSearch() {
        StepVerifier.create(
                client.get().uri("/attraction/detail/course?contentId=1952962")
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType("application/json")
                        .returnResult(CourseAttractionDetailVO.class)
                        .getResponseBody()

        ).assertNext(attractionDetailVO -> {
            assertNotNull(attractionDetailVO);
            assertEquals("0000", attractionDetailVO.getResultCode());
            assertEquals("OK", attractionDetailVO.getResultMessage());
            assertEquals(1, attractionDetailVO.getNumOfRows());
            assertEquals(1, attractionDetailVO.getPageNo());
            assertEquals(1, attractionDetailVO.getTotalCount());
            assertNotNull(attractionDetailVO.getDetail());
        }).verifyComplete();
    }

}
