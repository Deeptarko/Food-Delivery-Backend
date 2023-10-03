package com.foodka.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.foodka.dto.InventoryDto;
import com.foodka.dto.OrderRequest;
import com.foodka.entity.Order;
import com.foodka.entity.OrderItem;
import com.foodka.entity.OrderStatus;
import com.foodka.feign.InventoryClient;
import com.foodka.respository.OrderItemRepository;
import com.foodka.respository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final InventoryClient inventoryClient;
	private final ModelMapper mapper;

	public String saveOrder(List<Long> itemIds) {

		// Check if all the items corresponding to the ids are available
		List<InventoryDto> items = inventoryClient.inventoryItems(itemIds);
		System.out.println(items);

		List<OrderItem> orderItems = items.stream().map(item -> this.mapper.map(item, OrderItem.class))
				.collect(Collectors.toList());

		

		Order order = new Order();
		order.setItems(orderItems);
		order.setStatus(OrderStatus.PENDING);
		
		//Call the delivery service
		
		orderRepository.save(order);
		
		
		
		return "Success";

	}
}
