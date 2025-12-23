package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderResponseDto {

	private Long orderId;
	private LocalDateTime orderDate;
	private OrderStatus status;
	private double totalAmount;

}
