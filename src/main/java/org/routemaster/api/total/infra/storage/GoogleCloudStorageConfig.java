package org.routemaster.api.total.infra.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
@Component
@Getter
public class GoogleCloudStorageConfig {

    public static String credentialJson;
    public static String projectId;
    public static String bucketName;

    @Value("${cloud.gcp.storage.credentials.json}")
    public void setCredentialsPath(String credentialJson) {
        this.credentialJson = credentialJson;
    }

    @Value("${cloud.gcp.storage.project-id}")
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Value("${cloud.gcp.storage.bucket-name}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Bean
    public Storage storage() throws IOException {
        InputStream is = new ByteArrayInputStream(credentialJson.getBytes());
        GoogleCredentials credentials = GoogleCredentials.fromStream(is);
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }

}
