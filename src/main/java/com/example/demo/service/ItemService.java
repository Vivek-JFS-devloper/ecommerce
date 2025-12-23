package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ItemRequestDto;
import com.example.demo.dto.ItemResponseDto;

public interface ItemService {

	ItemResponseDto createItem(ItemRequestDto dto);

	List<ItemResponseDto> getAllItems();
	
	List<ItemResponseDto> searchItems(String name, String category, Double minPrice, Double maxPrice);

	ItemResponseDto getItemById(Long id);

	ItemResponseDto updateItem(Long id, ItemRequestDto dto);

	void deleteItem(Long id);

}
