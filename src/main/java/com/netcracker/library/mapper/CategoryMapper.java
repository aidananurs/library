
package com.netcracker.library.mapper;

import com.netcracker.library.entity.Category;
import com.netcracker.library.model.CategoryDto;
import com.netcracker.library.model.SaveCategoryRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
    Category toEntity(SaveCategoryRequest request);
}
