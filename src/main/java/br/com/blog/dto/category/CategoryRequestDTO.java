package br.com.blog.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        String id,
        @NotBlank(message = "It is required")
        String name
) {
}
