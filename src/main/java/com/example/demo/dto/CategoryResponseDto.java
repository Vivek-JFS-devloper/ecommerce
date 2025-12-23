package com.example.demo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long id;

    private String name;

    // Parent category id (null if root)
    private Long parentId;

    // Child categories
    private Set<CategoryResponseDto> children;
}
