package com.obs.Phones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.Phones.domain.PhonesReturned;

@Repository
public interface PhonesReturnedRepository extends CrudRepository<PhonesReturned,Long> {
	
}