package com.devrodts.commerce.commerce.modules.image.domain.entity;
import com.devrodts.commerce.commerce.modules.user.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.UUID;


@Getter
@Setter
@Entity(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;
    private String imageFileName;
    private String fileType;

    @Lob
    private Blob imageDataBlob;

    private String imageDownloadUrl;
    private Timestamp imageDownloadTime;
    private Timestamp imageCreationTime;
    private Timestamp imageUpdateTimestamp;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private UserEntity user;

}
