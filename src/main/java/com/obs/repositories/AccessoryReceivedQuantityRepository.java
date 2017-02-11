package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.AccessoryReceivedQuantity;

@Repository
public interface AccessoryReceivedQuantityRepository extends CrudRepository<AccessoryReceivedQuantity,Long> {

	List<AccessoryReceivedQuantity> findByOrderByReceivedDate();
	
}
