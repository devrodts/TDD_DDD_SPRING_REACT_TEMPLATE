package com.devrodts.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @Schema(
                description = "useremail@email.com",
                example = "useremail@email.com"
        )
        @NotBlank(message = "E-mail can't be empty.")
        @Email(message = "Incorrect e-mail format")
        String email,

        @Schema(
                description = "Senha do usuário",
                example = "senhaForte123"
        )
        @NotBlank(message = "A senha não pode ser vazia.")
        @Size(min = 8, message = "The password must be at least 8 characters.")
        String password
) {
}
