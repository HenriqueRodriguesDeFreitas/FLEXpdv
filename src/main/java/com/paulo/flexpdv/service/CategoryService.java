package com.paulo.flexpdv.service;

import com.paulo.flexpdv.dto.request.CategoryRequestDto;
import com.paulo.flexpdv.dto.response.CategoryResponseDto;
import com.paulo.flexpdv.exception.custom.EntityAlreadyExistsException;
import com.paulo.flexpdv.exception.custom.EntityNotFoundException;
import com.paulo.flexpdv.model.Category;
import com.paulo.flexpdv.model.CategoryMapper;
import com.paulo.flexpdv.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public CategoryResponseDto save(CategoryRequestDto requestDto) {
        var name = normalizeString(requestDto.name());
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new EntityAlreadyExistsException("Já existe uma categoria cadastrada com o nome: " + name);
        }

        Category newCategory = new Category(name);
        var categorySaved = categoryRepository.save(newCategory);
        return categoryMapper.toResponse(categorySaved);
    }

    @Transactional
    public CategoryResponseDto update(UUID idCategory, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma catégoria encontrada com o id: " + idCategory));

        var name = normalizeString(requestDto.name());
        boolean categoryAlreadyExists = categoryRepository.existsByNameIgnoreCaseAndIdNot(name, idCategory);
        if (categoryAlreadyExists) {
            throw new EntityAlreadyExistsException("Já existe uma categoria cadastrada com o nome: " + name);
        }

        category.setName(name);

        var updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(updatedCategory);

    }

    private String normalizeString(String text) {
        return (text == null) ? null : text.trim().replaceAll("\\s+", " ");
    }
}
