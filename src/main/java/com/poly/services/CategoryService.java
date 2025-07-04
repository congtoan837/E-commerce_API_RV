package com.poly.services;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.poly.dto.request.CategoryRequest;
import com.poly.dto.response.product.CategoryResponse;
import com.poly.entity.Category;
import com.poly.exception.AppException;
import com.poly.exception.ErrorCode;
import com.poly.mapper.CategoryMapper;
import com.poly.repositories.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getAll() {
        return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    public CategoryResponse update(CategoryRequest request) {
        Category category = categoryRepository
                .findById(request.getName())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategoryFromCategoryRequest(category, request);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public void delete(String id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
    }
}
