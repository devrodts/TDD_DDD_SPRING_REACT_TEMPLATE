package com.devrodts.commerce.commerce.modules.image.application.dtos;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.UUID;

public record ImageDTO(
        UUID imageID,
        String imageFileName,
        String fileType,
        Blob imageDataBlob,
        String imageDownloadUrl,
        Timestamp imageDownloadTime,
        Timestamp imageCreationTime,
        Timestamp imageUpdateTimestamp
) {
}
