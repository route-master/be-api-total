package org.routemaster.api.total.domain.file.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void testFileUpload() {
        // Create file to test file upload
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", "Hello, World!".getBytes())
                .header(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=file; filename=test-file.txt")
                .contentType(MediaType.TEXT_PLAIN);

        // request file upload
        client.post()
                .uri("/file/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(builder.build())
                .exchange()
                .expectStatus().isOk() // 예상되는 응답 상태
                .expectBody(String.class)
                .value(responseBody -> {
                    assert(responseBody.contains("https://storage.cloud.google.com/"));
                });
    }

}
