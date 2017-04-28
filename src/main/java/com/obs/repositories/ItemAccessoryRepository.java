package com.obs.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.obs.domain.ItemAccessory;

@Repository
public interface ItemAccessoryRepository extends CrudRepository<ItemAccessory,Long> {
	
	@Query("SELECT ia FROM ItemAccessory ia where ia.deleteYN = 'N'")
	List<ItemAccessory> findByDeleteYN();
	
	List<ItemAccessory> findByOrderByProductId();

	List<ItemAccessory> findByOrderByProductName();

	@Query("SELECT ia FROM ItemAccessory ia where ia.deleteYN = 'N'")
	Page<ItemAccessory> findByDeleteYNPageable(Pageable pageable);
}
