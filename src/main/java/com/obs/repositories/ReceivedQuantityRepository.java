package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.ReceivedQuantity;

@Repository
public interface ReceivedQuantityRepository extends CrudRepository<ReceivedQuantity,Long> {

	List<ReceivedQuantity> findByOrderByReceivedDate();
	
}
