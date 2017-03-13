package com.obs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.Master;

@Repository
public interface MasterRepository extends CrudRepository<Master,Long> {
	List<Master> findByOrderById();
}
