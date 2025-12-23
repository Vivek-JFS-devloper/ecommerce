package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.intThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.OrderItemRequestDto;
import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.OrderResponseDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private ItemRepository itemRepository;

	@InjectMocks
	private OrderServiceImpl serviceImpl;

	@Test
	void shouldCreateOrder() {
		Long id = 1L;
		int price = 100;
		int quantity = 2;

		Item item = new Item();
		item.setId(id);
		item.setPrice(price);

		OrderItemRequestDto dto = new OrderItemRequestDto();
		dto.setItemId(id);
		dto.setQuantity(quantity);

		OrderRequestDto requestDto = new OrderRequestDto();
		requestDto.setItems(List.of(dto));

		Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(item));

		// ACT
		OrderResponseDto responseDto = serviceImpl.createOrder(requestDto);

		// ASSERT
		assertEquals(OrderStatus.CREATED, responseDto.getStatus());
	}
}