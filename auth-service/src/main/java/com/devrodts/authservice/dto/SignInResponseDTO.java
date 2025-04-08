package com.devrodts.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponseDTO(
        @Schema(description = "Token de autenticação", example = "Bearer abc123def4564frj58hty")
        String token
) {
}
