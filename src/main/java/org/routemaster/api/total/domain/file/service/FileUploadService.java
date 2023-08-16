package org.routemaster.api.total.domain.file.service;

import org.springframework.http.codec.multipart.FilePart;

import java.io.IOException;

public interface FileUploadService {

    String uploadFileToGCS(FilePart file) throws IOException;

}
