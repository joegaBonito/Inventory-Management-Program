package com.obs.General.services;

import java.util.List;

import com.obs.General.domain.OrderEntity;

public interface OrderService {
	public List<OrderEntity> list();
	public OrderEntity save(OrderEntity orderEntity);
	public void delete(Long id);
}
