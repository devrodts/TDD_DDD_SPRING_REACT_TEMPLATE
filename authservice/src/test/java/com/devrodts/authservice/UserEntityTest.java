package com.devrodts.authservice;
import com.devrodts.authservice.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final UUID id = UUID.randomUUID();
    private final String validEmail = "test@example.com";
    private final String validPassword = "password123";
    private final String validRole = "USER";

    @Test
    @DisplayName("Should create a valid user")
    void shouldCreateValidUser() {
        User user = new User(id, validEmail, validPassword, validRole);

        assertEquals(id, user.id());
        assertEquals(validEmail, user.email());
        assertEquals(validPassword, user.password());
        assertEquals(validRole, user.role());
    }

    @Test
    @DisplayName("Should create a new user without ID")
    void shouldCreateUserWithoutId() {
        User user = User.createNew(validEmail, validPassword, validRole);

        assertNull(user.id());
        assertEquals(validEmail, user.email());
        assertEquals(validPassword, user.password());
        assertEquals(validRole, user.role());
    }

    @Test
    @DisplayName("Should create user with no-args constructor")
    void shouldCreateUserWithNoArgsConstructor() {
        User user = new User();

        assertNull(user.id());
        assertNull(user.email());
        assertNull(user.password());
        assertNull(user.role());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    @DisplayName("Should throw exception when email is invalid")
    void shouldThrowExceptionWhenEmailIsInvalid(String email) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, email, validPassword, validRole)
        );

        assertTrue(exception.getMessage().contains("email"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    @DisplayName("Should throw exception when password is invalid")
    void shouldThrowExceptionWhenPasswordIsInvalid(String password) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, validEmail, password, validRole)
        );

        assertTrue(exception.getMessage().contains("senha"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234", "123456", "1234567"})
    @DisplayName("Should throw exception when password is too short")
    void shouldThrowExceptionWhenPasswordIsTooShort(String password) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, validEmail, password, validRole)
        );

        assertTrue(exception.getMessage().contains("mínimo 8 caracteres"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    @DisplayName("Should throw exception when role is invalid")
    void shouldThrowExceptionWhenRoleIsInvalid(String role) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, validEmail, validPassword, role)
        );

        assertTrue(exception.getMessage().contains("role"));
    }

    @Test
    @DisplayName("Should not contain password in toString output")
    void shouldNotContainPasswordInToString() {
        User user = new User(id, validEmail, validPassword, validRole);
        String userString = user.toString();

        assertFalse(userString.contains(validPassword));
        assertTrue(userString.contains(validEmail));
        assertTrue(userString.contains(validRole));
    }

    @Test
    @DisplayName("Should verify equals and hashCode implementation")
    void shouldVerifyEqualsAndHashCode() {
        User user1 = new User(id, validEmail, validPassword, validRole);
        User user2 = new User(id, validEmail, validPassword, validRole);
        User user3 = new User(UUID.randomUUID(), validEmail, validPassword, validRole);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}