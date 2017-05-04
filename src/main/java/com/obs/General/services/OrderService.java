package com.obs.General.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.General.domain.OrderEntity;
import com.obs.General.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<OrderEntity> list() {
		return orderRepository.findByOrderByOrderId();
		
	}

	public OrderEntity save(OrderEntity orderEntity) {
		return orderRepository.save(orderEntity);
	}

	public void delete(Long id) {	
		orderRepository.delete(id);
	}
	
}
