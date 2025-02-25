package com.netcracker.library.controller;

import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.model.CategoryDto;
import com.netcracker.library.model.SaveCategoryRequest;
import com.netcracker.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            var categories = categoryService.getAll();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Optional<CategoryDto> category = categoryService.getById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody SaveCategoryRequest request) {
        try {
            categoryService.save(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody SaveCategoryRequest request) {
        try {
            categoryService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return createInternalServerErrorStatus(e.getMessage());
        }
    }

    private ResponseEntity<?> createInternalServerErrorStatus(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + message);
    }
}
