package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ItemRequestDto;
import com.example.demo.dto.ItemResponseDto;
import com.example.demo.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Items")
@RestController
@RequestMapping("/api/items")

public class ItemController {
	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Operation(summary = "Create a new item.")
	@PostMapping
	public ResponseEntity<ItemResponseDto> createItem(@Valid @RequestBody ItemRequestDto dto) {
		return new ResponseEntity<>(itemService.createItem(dto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Retrieve all items.")
	@GetMapping
	public ResponseEntity<List<ItemResponseDto>> getAllItems() {
		return ResponseEntity.ok(itemService.getAllItems());
	}
	
	@Operation(summary = "Ability to search items by name, category, price")
	@GetMapping("/search")
    public ResponseEntity<List<ItemResponseDto>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        return ResponseEntity.ok(
                itemService.searchItems(name, category, minPrice, maxPrice)
        );
    }
	
	@Operation(summary = "etrieve a specific item by ID.")
	@GetMapping("/{itemId}")
	public ResponseEntity<ItemResponseDto> getItem(@PathVariable("itemId") Long id) {
		return ResponseEntity.ok(itemService.getItemById(id));
	}
	
	@Operation(summary = "Update an existing item.")
	@PutMapping("/{itemId}")
	public ResponseEntity<ItemResponseDto> updateItem(@PathVariable("itemId") Long id, @Valid @RequestBody ItemRequestDto dto) {
		return ResponseEntity.ok(itemService.updateItem(id, dto));
	}
	
	@Operation(summary = "Delete an item.")
	@DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
