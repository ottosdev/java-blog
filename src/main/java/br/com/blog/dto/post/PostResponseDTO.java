package br.com.blog.dto.post;

import br.com.blog.dto.category.CategoryDTO;

public record PostResponseDTO(
        String id,
        String title,
        String content,
        String author,
        String categoryName
) {
}