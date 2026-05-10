package com.paulo.flexpdv.service;

import com.paulo.flexpdv.dto.request.CategoryRequestDto;
import com.paulo.flexpdv.dto.response.CategoryResponseDto;
import com.paulo.flexpdv.exception.custom.EntityAlreadyExistsException;
import com.paulo.flexpdv.model.Category;
import com.paulo.flexpdv.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryResponseDto save(CategoryRequestDto requestDto) {
        var name = requestDto.name().trim();
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new EntityAlreadyExistsException("Já existe uma categoria cadastrada com o nome: " + name);
        }

        Category newCategory = new Category(name);
        var categorySaved = categoryRepository.save(newCategory);
        return new CategoryResponseDto(categorySaved.getId(), categorySaved.getName());
    }
}
