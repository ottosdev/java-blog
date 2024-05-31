package br.com.blog.services;

import br.com.blog.dto.category.CategoryRequestDTO;
import br.com.blog.dto.category.CategoryResponseDTO;
import br.com.blog.exceptions.CustomException;
import br.com.blog.model.Category;
import br.com.blog.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream().map(
                category -> new CategoryResponseDTO(
                        category.getId(),
                        category.getName()
                )
        ).collect(Collectors.toList());
    }

    @Transactional
    public Category save(CategoryRequestDTO dto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(dto.name());
        if (categoryOptional.isPresent()) {
            throw new CustomException("Category name already exists!", HttpStatus.CONFLICT, "409");
        }
        Category category = new Category(dto);
        return categoryRepository.save(category);
    }
}
