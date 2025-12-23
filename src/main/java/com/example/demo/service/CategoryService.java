package com.example.demo.service;

import java.util.Set;

import com.example.demo.dto.CategoryRequestDto;
import com.example.demo.dto.CategoryResponseDto;

public interface CategoryService {

    // Create new category
    CategoryResponseDto createCategory(CategoryRequestDto dto);

    // Update existing category
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto);

    // Delete category by id
    void deleteCategory(Long id);

    // Get category by id
    CategoryResponseDto getCategoryById(Long id);

    // Get all categories (hierarchy)
    Set<CategoryResponseDto> getAllCategories();
}
