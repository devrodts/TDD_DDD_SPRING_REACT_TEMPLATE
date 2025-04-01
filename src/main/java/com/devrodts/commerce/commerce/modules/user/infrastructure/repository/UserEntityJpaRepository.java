package com.devrodts.commerce.commerce.modules.user.infrastructure.repository;
import com.devrodts.commerce.commerce.modules.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserEntityJpaRepository extends JpaRepository<UserEntity, UUID> {

}
