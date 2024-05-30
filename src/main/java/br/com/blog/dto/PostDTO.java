package br.com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostDTO(
        String id,
        @NotBlank(message = "It is required")
        String title,
        @NotBlank(message = "It is required")
        String content,
        @NotBlank(message = "It is required")
        String author,
        @NotBlank(message = "It is required")
        String category
) {
}
