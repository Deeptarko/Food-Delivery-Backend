package com.foodka.service;

import java.util.ArrayList;
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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final InventoryClient inventoryClient;
	private final ModelMapper mapper;
	
	public String saveOrder(List<Long> itemIds) {
		
		Order order = new Order();
		
		// Check if all the items corresponding to the ids are available
		List<InventoryDto> items = inventoryClient.inventoryItems(itemIds);
		System.out.println(items);

		
		List<OrderItem>orderItems=new ArrayList<>();
		
		for(InventoryDto item:items) {
			OrderItem orderItem=new OrderItem();
			orderItem.setName(item.getName());
			orderItem.setPrice(item.getPrice());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
			
		}

		System.out.println(orderItems);

		
		order.setItems(orderItems);
		order.setStatus(OrderStatus.PENDING);
		
		//Call the delivery service
		
		orderRepository.save(order);
		
		System.out.println(orderItems);
		
		return "Success";

	}
}
