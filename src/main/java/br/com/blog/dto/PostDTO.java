package br.com.blog.dto;

public record PostDTO(
        String title,
        String content,
        String author,
        String category
) {
}
