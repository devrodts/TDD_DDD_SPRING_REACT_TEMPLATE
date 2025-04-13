package com.devrodts.authservice;
import com.devrodts.authservice.entity.User;
import com.devrodts.authservice.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User createTestUser(String email, String role) {
        return User.createNew(email, "password12345", role);
    }

    @Test
    @DisplayName("Should find user by email ignoring case")
    void shouldFindUserByEmailIgnoringCase() {
        // Given
        User savedUser = entityManager.persist(createTestUser("test@example.com", "USER"));
        entityManager.flush();

        // When
        Optional<User> foundUserLowerCase = userRepository.findByEmailIgnoreCase("test@example.com");
        Optional<User> foundUserUpperCase = userRepository.findByEmailIgnoreCase("TEST@EXAMPLE.COM");
        Optional<User> foundUserMixedCase = userRepository.findByEmailIgnoreCase("Test@Example.com");
        Optional<User> notFound = userRepository.findByEmailIgnoreCase("nonexistent@example.com");

        // Then
        assertTrue(foundUserLowerCase.isPresent());
        assertEquals(savedUser.email(), foundUserLowerCase.get().email());

        assertTrue(foundUserUpperCase.isPresent());
        assertEquals(savedUser.email(), foundUserUpperCase.get().email());

        assertTrue(foundUserMixedCase.isPresent());
        assertEquals(savedUser.email(), foundUserMixedCase.get().email());

        assertTrue(notFound.isEmpty());
    }

    @Test
    @DisplayName("Should check if email exists")
    void shouldCheckIfEmailExists() {
        // Given
        entityManager.persist(createTestUser("exists@example.com", "USER"));
        entityManager.flush();

        // When & Then
        assertTrue(userRepository.existsByEmailIgnoreCase("exists@example.com"));
        assertTrue(userRepository.existsByEmailIgnoreCase("EXISTS@EXAMPLE.COM"));
        assertFalse(userRepository.existsByEmailIgnoreCase("notexists@example.com"));
    }

    @Test
    @DisplayName("Should find users by role")
    void shouldFindUsersByRole() {
        // Given
        entityManager.persist(createTestUser("user1@example.com", "ADMIN"));
        entityManager.persist(createTestUser("user2@example.com", "USER"));
        entityManager.persist(createTestUser("user3@example.com", "USER"));
        entityManager.flush();

        // When
        List<User> users = userRepository.findByRole("USER");
        List<User> admins = userRepository.findByRole("ADMIN");
        List<User> moderators = userRepository.findByRole("MODERATOR");

        // Then
        assertEquals(2, users.size());
        assertEquals(1, admins.size());
        assertTrue(moderators.isEmpty());
    }

    @Test
    @DisplayName("Should find users by role with pagination")
    void shouldFindUsersByRoleWithPagination() {
        // Given
        for (int i = 0; i < 25; i++) {
            entityManager.persist(createTestUser("user" + i + "@example.com", "USER"));
        }
        entityManager.flush();

        // When
        Page<User> firstPage = userRepository.findByRole("USER", PageRequest.of(0, 10));
        Page<User> secondPage = userRepository.findByRole("USER", PageRequest.of(1, 10));
        Page<User> thirdPage = userRepository.findByRole("USER", PageRequest.of(2, 10));

        // Then
        assertEquals(10, firstPage.getContent().size());
        assertEquals(10, secondPage.getContent().size());
        assertEquals(5, thirdPage.getContent().size());
        assertEquals(25, firstPage.getTotalElements());
        assertEquals(3, firstPage.getTotalPages());
    }

    @Test
    @DisplayName("Should find users by email pattern")
    void shouldFindUsersByEmailPattern() {
        // Given
        entityManager.persist(createTestUser("john.doe@example.com", "USER"));
        entityManager.persist(createTestUser("jane.doe@example.com", "USER"));
        entityManager.persist(createTestUser("alice@gmail.com", "USER"));
        entityManager.flush();

        List<User> exampleUsers = userRepository.findByEmailContainingIgnoreCase("example");
        List<User> doeUsers = userRepository.findByEmailContainingIgnoreCase("doe");
        List<User> gmailUsers = userRepository.findByEmailContainingIgnoreCase("gmail");

        assertEquals(2, exampleUsers.size());
        assertEquals(2, doeUsers.size());
        assertEquals(1, gmailUsers.size());
    }

    @Test
    @DisplayName("Should update user role")
    void shouldUpdateUserRole() {
        // Given
        User user = entityManager.persist(createTestUser("user@example.com", "USER"));
        entityManager.flush();
        UUID userId = user.id();

        int updatedCount = userRepository.updateUserRole(userId, "ADMIN");
        entityManager.clear(); // Clear persistence context to force reload from DB
        User updatedUser = entityManager.find(User.class, userId);

        assertEquals(1, updatedCount);
        assertEquals("ADMIN", updatedUser.role());
    }

    @Test
    @DisplayName("Should update user password")
    void shouldUpdateUserPassword() {
        User user = entityManager.persist(createTestUser("user@example.com", "USER"));
        entityManager.flush();
        UUID userId = user.id();
        String newPassword = "newPassword123";

        int updatedCount = userRepository.updatePassword(userId, newPassword);
        entityManager.clear(); // Clear persistence context to force reload from DB
        User updatedUser = entityManager.find(User.class, userId);

        assertEquals(1, updatedCount);
        assertEquals(newPassword, updatedUser.password());
    }

    @Test
    @DisplayName("Should delete users by role")
    void shouldDeleteUsersByRole() {
        entityManager.persist(createTestUser("user1@example.com", "TEMP"));
        entityManager.persist(createTestUser("user2@example.com", "TEMP"));
        entityManager.persist(createTestUser("admin@example.com", "ADMIN"));
        entityManager.flush();

        long deletedCount = userRepository.deleteByRole("TEMP");
        List<User> remainingUsers = userRepository.findAll();

        assertEquals(2, deletedCount);
        assertEquals(1, remainingUsers.size());
        assertEquals("ADMIN", remainingUsers.get(0).role());
    }

    @Test
    @DisplayName("Should count users by role")
    void shouldCountUsersByRole() {
        entityManager.persist(createTestUser("user1@example.com", "USER"));
        entityManager.persist(createTestUser("user2@example.com", "USER"));
        entityManager.persist(createTestUser("admin@example.com", "ADMIN"));
        entityManager.flush();

        long userCount = userRepository.countByRole("USER");
        long adminCount = userRepository.countByRole("ADMIN");
        long modCount = userRepository.countByRole("MODERATOR");

        assertEquals(2, userCount);
        assertEquals(1, adminCount);
        assertEquals(0, modCount);
    }

}