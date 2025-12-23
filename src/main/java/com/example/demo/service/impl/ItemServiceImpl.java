package com.example.demo.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.design.ItemBuilder;
import com.example.demo.dto.ItemRequestDto;
import com.example.demo.dto.ItemResponseDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    // ---------------- CRUD ----------------

    @Override
    public ItemResponseDto createItem(ItemRequestDto dto) {
        Set<Category> categories = getCategories(dto.getCategoryIds());

        Item item = ItemBuilder.build(dto, categories);
        Item savedItem = itemRepository.save(item);

        return mapToResponse(savedItem);
    }

    @Override
    public List<ItemResponseDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList(); // Java 17
    }

    @Override
    public ItemResponseDto getItemById(Long id) {
        Item item = findItemById(id);
        return mapToResponse(item);
    }

    @Override
    public ItemResponseDto updateItem(Long id, ItemRequestDto dto) {
        Item item = findItemById(id);

        Set<Category> categories = getCategories(dto.getCategoryIds());

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCategories(categories);

        return mapToResponse(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        Item item = findItemById(id);
        itemRepository.delete(item);
    }

    // ---------------- Helper Methods ----------------

    private Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    private Set<Category> getCategories(Set<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return Set.of();
        }
        return categoryRepository.findAllById(categoryIds)
                .stream()
                .collect(Collectors.toSet());
    }
    
    
    
    @Override
    public List<ItemResponseDto> searchItems(String name, String category, Double minPrice, Double maxPrice) {

        List<Item> items;

        if (name != null) {
            items = itemRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null) {
            items = itemRepository.findByCategoryName(category);
        } else if (minPrice != null && maxPrice != null) {
            items = itemRepository.findByPriceBetween(minPrice, maxPrice);
        } else {
            items = itemRepository.findAll();
        }

        return items.stream()
                .map(this::mapToResponse)
                .toList();
    }
    

    private ItemResponseDto mapToResponse(Item item) {
        return ItemResponseDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .categories(
                        item.getCategories()
                                .stream()
                                .map(Category::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
