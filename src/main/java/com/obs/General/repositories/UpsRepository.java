package com.obs.General.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.obs.General.domain.UpsOrder;

@Repository
public interface UpsRepository extends PagingAndSortingRepository<UpsOrder, Long> {
	
	List<UpsOrder> findByOrderBySystemId();
	Page<UpsOrder> findAll(Pageable pageable);
	List<UpsOrder> findByOrderByUpsOrderReceived();
	
}
