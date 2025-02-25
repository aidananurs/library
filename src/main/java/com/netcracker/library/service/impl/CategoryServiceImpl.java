package com.netcracker.library.service.impl;

import com.netcracker.library.entity.Category;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.CategoryMapper;
import com.netcracker.library.model.CategoryDto;
import com.netcracker.library.model.SaveCategoryRequest;
import com.netcracker.library.repository.CategoryRepository;
import com.netcracker.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void save(SaveCategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void update(Long id, SaveCategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));

        existingCategory.setCategoryName(request.categoryName());
        categoryRepository.save(existingCategory);
    }

    @Override
    public Optional<CategoryDto> getById(Long categoryId) {
        return categoryRepository.findById(categoryId).map(categoryMapper::toDto);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
