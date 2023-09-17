package org.routemaster.api.total.domain.file.service.impl;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.file.service.FileUploadService;
import org.routemaster.api.total.infra.storage.GoogleCloudStorageConfig;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultFileUploadService implements FileUploadService {

    private final Storage storage;
    private final String bucketName = GoogleCloudStorageConfig.bucketName;

    public String uploadFileToGCS(FilePart file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String contentType = file.headers().get("Content-Type").get(0).toString();
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(contentType)
                        .build(),
                getInputStreamFromFluxDataBuffer(file.content()));
        return "https://storage.cloud.google.com/" + bucketName + "/" + uuid;
    }

    InputStream getInputStreamFromFluxDataBuffer(Flux<DataBuffer> data) throws IOException {
        PipedOutputStream osPipe = new PipedOutputStream();
        PipedInputStream isPipe = new PipedInputStream(osPipe);
        DataBufferUtils.write(data, osPipe)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnComplete(() -> {
                    try {
                        osPipe.close();
                    } catch (IOException ignored) {
                    }
                })
                .subscribe(DataBufferUtils.releaseConsumer());
        return isPipe;
    }
}
