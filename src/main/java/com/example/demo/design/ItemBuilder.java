package com.example.demo.design;

import java.util.Set;

import com.example.demo.dto.ItemRequestDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Item;

public class ItemBuilder {

    private ItemBuilder() {}

    public static Item build(ItemRequestDto request, Set<Category> categories) {
        return Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .categories(categories)
                .build();
    }
    
    public static void update(Item item, ItemRequestDto dto, Set<Category> categories) {
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCategories(categories);
    }
}

