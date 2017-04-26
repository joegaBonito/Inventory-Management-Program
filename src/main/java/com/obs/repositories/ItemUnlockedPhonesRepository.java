package com.obs.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.domain.ItemUnlockedPhone;

@Repository
public interface ItemUnlockedPhonesRepository extends CrudRepository<ItemUnlockedPhone, Long>  {
	List<ItemUnlockedPhone> findByOrderByProductId();
	List<ItemUnlockedPhone> findByOrderByProductName();
	List<ItemUnlockedPhone> findByOrderByItemUnlockedPhoneId();
	Page<ItemUnlockedPhone> findAll(Pageable pageable);
}
