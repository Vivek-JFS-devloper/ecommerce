package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.OrderResponseDto;
import com.example.demo.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Orders")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Operation(summary = "Create a new order.")
	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto dto) {
		return new ResponseEntity<>(orderService.createOrder(dto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Retrieve all orders.")
	@GetMapping
	public ResponseEntity<List<OrderResponseDto>> getOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	@Operation(summary = "Retrieve a specific order by ID.")
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("orderId") Long id) {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	
	@Operation(summary = "Cancel an order.")
	@DeleteMapping("/{orderId}")
	public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable("orderId") Long id) {
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}
}
