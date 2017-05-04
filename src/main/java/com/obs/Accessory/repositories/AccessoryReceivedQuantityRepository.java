package com.obs.Accessory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.Accessory.domain.AccessoryReceivedQuantity;

@Repository
public interface AccessoryReceivedQuantityRepository extends CrudRepository<AccessoryReceivedQuantity,Long> {

	List<AccessoryReceivedQuantity> findByOrderByReceivedDate();
	
}
