package com.obs.Phones.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.repositories.ItemUnlockedPhonesRepository;
import com.obs.Phones.services.ItemUnlockedPhonesService;

@Service
public class ItemUnlockedPhonesServiceImpl implements ItemUnlockedPhonesService {
	
	private ItemUnlockedPhonesRepository itemUnlockedPhonesRepository;
	
	@Autowired
	public ItemUnlockedPhonesServiceImpl(ItemUnlockedPhonesRepository itemUnlockedPhonesRepository) {
		this.itemUnlockedPhonesRepository = itemUnlockedPhonesRepository;
	}

	public ItemUnlockedPhone save(ItemUnlockedPhone itemUnlockedPhone) {
		return itemUnlockedPhonesRepository.save(itemUnlockedPhone);
	}
	
	public List<ItemUnlockedPhone> itemUnlockedPhoneIdList() {
		return itemUnlockedPhonesRepository.findByOrderByItemUnlockedPhoneId();
	}

	public List<ItemUnlockedPhone> list() {
		return itemUnlockedPhonesRepository.findByDeleteYNOrderByProductId();
	}

	public ItemUnlockedPhone get(Long itemUnlockedPhoneId) {
		return itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
	}
	
	/*
	 * Changes the column delete_YN to Y, instead of completely deleting it from DB.
	 */
	public void delete(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		item.setDeleteYN('Y');
		save(item);
	}
	
	public String getProductId(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		return item.getProductId();
	}
	public String getProductName(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		return item.getProductName();
	}
	public double getLength(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		return item.getUpLength();
	}
	public double getHeight(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		return item.getUpHeight();
	}
	public double getWidth(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		return item.getUpWidth();
	}
	public double getWeight(Long itemUnlockedPhoneId) {
		ItemUnlockedPhone item = itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
		return item.getUpWeight();
	}

	public List<ItemUnlockedPhone> productNameList() {
		return itemUnlockedPhonesRepository.findByOrderByProductName();
	}

	public Page<ItemUnlockedPhone> findByDeleteYNPageableOrderByProductId(Pageable pageable) {
		return itemUnlockedPhonesRepository.findByDeleteYNPageableOrderByProductId(pageable);
	}

}
