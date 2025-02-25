
package com.netcracker.library.service;

import com.netcracker.library.model.CategoryDto;
import com.netcracker.library.model.SaveCategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void save(SaveCategoryRequest request);
    void update(Long id, SaveCategoryRequest request);
    Optional<CategoryDto> getById(Long categoryId);
    List<CategoryDto> getAll();
    void deleteById(Long categoryId);
}
