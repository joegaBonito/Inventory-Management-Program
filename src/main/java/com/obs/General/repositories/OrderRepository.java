package com.obs.General.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.General.domain.OrderEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
	
	List<OrderEntity> findByOrderByOrderId();
	

}
