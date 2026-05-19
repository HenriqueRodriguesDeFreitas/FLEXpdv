package com.paulo.flexpdv.service;

import com.paulo.flexpdv.dto.request.CategoryRequestDto;
import com.paulo.flexpdv.dto.response.CategoryResponseDto;
import com.paulo.flexpdv.model.Category;
import com.paulo.flexpdv.model.CategoryMapper;
import com.paulo.flexpdv.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void save_If_ReturnedTrue() {
        var categoryRequest = new CategoryRequestDto("Bebida");
        var category = new Category("Bebida");

        when(categoryRepository.existsByNameIgnoreCase("Bebida")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryMapper.toResponse(any(Category.class))).thenAnswer(invocation -> {
            Category categoryResponse = invocation.getArgument(0);
            return new CategoryResponseDto(categoryResponse.getId(), categoryResponse.getName());
        });

        CategoryResponseDto response = categoryService.save(categoryRequest);

        assertNotNull(response, "Retorno não pode ser null");
        assertEquals(category.getName(), response.name(), "Nomes não coincidem");

    }

    @Test
    void update() {
    }
}