package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.UpsOrder;

@Repository
public interface UpsRepository extends PagingAndSortingRepository<UpsOrder, Long> {
	List<UpsOrder> findByOrderByUpsOrderReceivedDesc();

	List<UpsOrder> findByOrderBySystemId();
}
