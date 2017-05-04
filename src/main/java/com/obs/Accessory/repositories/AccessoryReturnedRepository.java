package com.obs.Accessory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.Accessory.domain.AccessoryReturned;

@Repository
public interface AccessoryReturnedRepository extends CrudRepository<AccessoryReturned,Long> {
	
}