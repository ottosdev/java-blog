package br.com.blog.controller;

import br.com.blog.dto.category.CategoryDTO;
import br.com.blog.dto.category.CategoryResponseDTO;
import br.com.blog.model.Category;
import br.com.blog.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryServices categoryService;
    public CategoryController(CategoryServices categoryServices) {
        this.categoryService = categoryServices;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryDTO dto) {
            Category category = categoryService.save(dto);
            CategoryResponseDTO categoryDTO = new CategoryResponseDTO(category.getId(), category.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

}
