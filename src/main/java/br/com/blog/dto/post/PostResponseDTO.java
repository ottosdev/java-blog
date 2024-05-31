package br.com.blog.dto.post;

public record PostResponseDTO(
        String id,
        String title,
        String content,
        String author,
        String categoryName
) {
}