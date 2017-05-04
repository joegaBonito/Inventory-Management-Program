package com.obs.Phones.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.Phones.domain.PhonesReceivedQuantity;

@Repository
public interface PhonesReceivedQuantityRepository extends CrudRepository<PhonesReceivedQuantity, Long>{
	List<PhonesReceivedQuantity> findByOrderByReceivedDate();
}
