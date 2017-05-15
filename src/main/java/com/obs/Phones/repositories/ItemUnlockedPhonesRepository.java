package com.obs.Phones.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obs.Phones.domain.ItemUnlockedPhone;

@Repository
public interface ItemUnlockedPhonesRepository extends CrudRepository<ItemUnlockedPhone, Long>  {
	@Query("SELECT ip FROM ItemUnlockedPhone ip where ip.deleteYN = 'N'")
	List<ItemUnlockedPhone> findByDeleteYN();
	
	@Query("SELECT ip FROM ItemUnlockedPhone ip where ip.deleteYN = 'N' ORDER BY ip.productId")
	List<ItemUnlockedPhone> findByDeleteYNOrderByProductId();
	
	List<ItemUnlockedPhone> findByOrderByProductId();
	List<ItemUnlockedPhone> findByOrderByProductName();
	List<ItemUnlockedPhone> findByOrderByItemUnlockedPhoneId();
	@Query("SELECT ip FROM ItemUnlockedPhone ip where ip.deleteYN = 'N' ORDER BY ip.productId")
	Page<ItemUnlockedPhone> findByDeleteYNPageableOrderByProductId(Pageable pageable);
}
