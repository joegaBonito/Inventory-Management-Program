package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.UpsOrder;

@Repository
public interface UpsRepository extends CrudRepository<UpsOrder, Long> {
	List<UpsOrder> findByOrderByUpsOrderReceivedDesc();

	List<UpsOrder> findByOrderBySystemId();
}
