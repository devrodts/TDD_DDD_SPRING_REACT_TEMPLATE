package com.devrodts.commerce.commerce.modules.image.infrastructure.repository;

import com.devrodts.commerce.commerce.modules.image.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, UUID> {
}
