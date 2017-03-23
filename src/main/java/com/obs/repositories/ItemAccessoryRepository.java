package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.ItemAccessory;

@Repository
public interface ItemAccessoryRepository extends CrudRepository<ItemAccessory,Long> {
	
	List<ItemAccessory> findByOrderByProductId();

	List<ItemAccessory> findByOrderByProductName();
}
