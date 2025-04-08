package com.devrodts.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        UUID id,

        @Column(unique = true, nullable = false)
        String email,

        @Column(nullable = false)
        String password,

        @Column(nullable = false)
        String role
) {
    public User() {
        this(null, null, null, null);
    }
    public User {
        if (!(id == null && email == null && password == null && role == null)) {
            validate(email, password, role);
        }
    }


    public static User createNew(String email, String password, String role) {
        validate(email, password, role);
        return new User(null, email, password, role);
    }

    private static void validate(String email, String password, String role) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("O role não pode ser nulo ou vazio");
        }
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", email=" + email + ", role=" + role + "]";
    }
}