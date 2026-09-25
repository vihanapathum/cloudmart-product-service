package com.cloudmart.productservice.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Uploads product images to a Google Cloud Storage bucket - satisfies the
 * module's mandatory Cloud Storage requirement.
 *
 * The Storage client is created lazily (on first real use) rather than at
 * Spring bean construction time, so the application still starts up fine
 * on a laptop with no GCP credentials configured; it only needs valid
 * Application Default Credentials when an image is actually uploaded.
 * On a GCP VM with the correct service account attached, this works with
 * zero extra configuration.
 */
@Service
public class GcsStorageService {

    @Value("${gcs.bucket-name}")
    private String bucketName;

    private volatile Storage storage;

    private Storage getStorage() {
        if (storage == null) {
            synchronized (this) {
                if (storage == null) {
                    storage = StorageOptions.getDefaultInstance().getService();
                }
            }
        }
        return storage;
    }

    public String uploadFile(MultipartFile file, String objectName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();
        getStorage().create(blobInfo, file.getBytes());
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, objectName);
    }
}
