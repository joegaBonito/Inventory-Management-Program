package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.domain.ItemUnlockedPhone;
import com.obs.repositories.ItemUnlockedPhonesRepository;

@Service
public class ItemUnlockedPhonesService {
	
	@Autowired
	ItemUnlockedPhonesRepository itemUnlockedPhonesRepository;
	
	public ItemUnlockedPhonesService(ItemUnlockedPhonesRepository itemUnlockedPhonesRepository) {
		this.itemUnlockedPhonesRepository = itemUnlockedPhonesRepository;
	}

	public ItemUnlockedPhone save(ItemUnlockedPhone itemUnlockedPhone) {
		return itemUnlockedPhonesRepository.save(itemUnlockedPhone);
	}
	
	public List<ItemUnlockedPhone> itemUnlockedPhoneIdList() {
		return itemUnlockedPhonesRepository.findByOrderByItemUnlockedPhoneId();
	}

	public List<ItemUnlockedPhone> list() {
		return itemUnlockedPhonesRepository.findByOrderByProductId();
	}

	public ItemUnlockedPhone get(Long itemUnlockedPhoneId) {
		return itemUnlockedPhonesRepository.findOne(itemUnlockedPhoneId);
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

}
