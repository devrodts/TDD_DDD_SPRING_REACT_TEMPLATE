package com.devrodts.authservice.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.devrodts.authservice.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<User> findByRole(String role);
    Page<User> findByRole(String role, Pageable pageable);
    List<User> findByEmailContainingIgnoreCase(String emailPattern);
    @Query("SELECT u FROM User u WHERE u.role = :role")
    Page<User> findUsersByRole(@Param("role") String role, Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.role = :newRole WHERE u.id = :userId")
    int updateUserRole(@Param("userId") UUID userId, @Param("newRole") String newRole);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.id = :userId")
    int updatePassword(@Param("userId") UUID userId, @Param("newPassword") String newPassword);
    @Modifying
    @Transactional
    long deleteByRole(String role);
    long countByRole(String role);
    @Query(value = "SELECT * FROM users u WHERE u.created_at >= NOW() - INTERVAL :days DAY", nativeQuery = true)
    List<User> findRecentUsers(@Param("days") int days);

    // Find users who haven't logged in for a specific period
    @Query(value = "SELECT * FROM users u WHERE u.last_login_at <= NOW() - INTERVAL :days DAY", nativeQuery = true)
    List<User> findInactiveUsers(@Param("days") int days);
}