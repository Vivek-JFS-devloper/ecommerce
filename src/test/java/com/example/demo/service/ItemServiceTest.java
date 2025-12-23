package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.ItemRequestDto;
import com.example.demo.dto.ItemResponseDto;
import com.example.demo.entity.Item;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.impl.ItemServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private ItemServiceImpl serviceImpl;

	@Test
	void shouldCreateItemSuccessfully() {

		ItemRequestDto dto = new ItemRequestDto();
		dto.setName("Laptop");
		dto.setPrice(76499.00);

		Item item = new Item();
		item.setId(1L);
		item.setName("Laptop");
		item.setPrice(76499.00);

		Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

		ItemResponseDto responseDto = serviceImpl.createItem(dto);
		assertNotNull(responseDto);
		assertEquals("Laptop", responseDto.getName());
		assertEquals(76499.00, responseDto.getPrice());
	}

	@Test
	void shouldThrowExceptionWhenItemNotFound() {

		Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> serviceImpl.getItemById(1L));
	}
}
