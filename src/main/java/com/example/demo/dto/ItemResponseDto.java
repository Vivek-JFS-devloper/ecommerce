package com.example.demo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemResponseDto {

	private Long id;
	private String name;
	private String description;
	private double price;
	private Set<String> categories;
}
