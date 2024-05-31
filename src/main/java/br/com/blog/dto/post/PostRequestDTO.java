package br.com.blog.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PostRequestDTO(
        @NotBlank(message = "It is required")
        String title,
        @NotBlank(message = "It is required")
        String content,
        @NotBlank(message = "It is required")
        String author,
        @NotBlank(message = "It is required")
        String categoryName
) {
}
