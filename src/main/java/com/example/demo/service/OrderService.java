package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.OrderResponseDto;

public interface OrderService {
	
	OrderResponseDto createOrder(OrderRequestDto dto);

	List<OrderResponseDto> getAllOrders();

	OrderResponseDto getOrderById(Long id);

	OrderResponseDto cancelOrder(Long id);
}
