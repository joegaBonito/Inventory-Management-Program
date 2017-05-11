package com.obs.Phones.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.obs.Phones.domain.ItemUnlockedPhone;

public interface ItemUnlockedPhonesService {
	public ItemUnlockedPhone save(ItemUnlockedPhone itemUnlockedPhone);
	public List<ItemUnlockedPhone> itemUnlockedPhoneIdList();
	public List<ItemUnlockedPhone> list();
	public ItemUnlockedPhone get(Long itemUnlockedPhoneId);
	public void delete(Long itemUnlockedPhoneId);
	public String getProductId(Long itemUnlockedPhoneId);
	public String getProductName(Long itemUnlockedPhoneId);
	public double getLength(Long itemUnlockedPhoneId);
	public double getHeight(Long itemUnlockedPhoneId);
	public double getWidth(Long itemUnlockedPhoneId);
	public double getWeight(Long itemUnlockedPhoneId);
	public List<ItemUnlockedPhone> productNameList();
	public Page<ItemUnlockedPhone> findByDeleteYNPageable(Pageable pageable);
}
