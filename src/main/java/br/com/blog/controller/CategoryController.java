package br.com.blog.controller;

import br.com.blog.dto.category.CategoryDTO;
import br.com.blog.dto.category.CategoryResponseDTO;
import br.com.blog.exceptions.CustomException;
import br.com.blog.model.Category;
import br.com.blog.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Object> createCategory(@RequestBody @Valid CategoryDTO dto) {
            Category category = categoryService.save(dto);
            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

}
