package com.obs.Accessory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.repositories.ItemAccessoryRepository;
import com.obs.Accessory.services.ItemAccessoryService;

@Service
public class ItemAccessoryServiceImpl implements ItemAccessoryService {
	
	private ItemAccessoryRepository itemAccessoryRepository;
	
	public ItemAccessoryServiceImpl() {}
	@Autowired
	public ItemAccessoryServiceImpl(ItemAccessoryRepository itemAccessoryRepository) {
		super();
		this.itemAccessoryRepository = itemAccessoryRepository;
	}
	
	public List<ItemAccessory> list(){ 
		return itemAccessoryRepository.findByDeleteYNOrderByProductId();
	}
	
	public List<ItemAccessory> productNameList() {
		return itemAccessoryRepository.findByOrderByProductName();
	}
	
	public ItemAccessory save(ItemAccessory itemAccessory) {
		return itemAccessoryRepository.save(itemAccessory);
	}

	public ItemAccessory get(Long itemAccessoryId) {
		return itemAccessoryRepository.findOne(itemAccessoryId);
	}
	
	public void deleteYN(Long itemAccessoryId) {    
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		item.setDeleteYN('Y');
		save(item);
	}
	
	public String getProductId(Long itemAccessoryId) {
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		return item.getProductId();
	}
	public String getProductName(Long itemAccessoryId) {
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		return item.getProductName();
	}
	public double getLength(Long itemAccessoryId) {
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		return item.getAccLength();
	}
	public double getHeight(Long itemAccessoryId) {
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		return item.getAccHeight();
	}
	public double getWidth(Long itemAccessoryId) {
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		return item.getAccWidth();
	}
	public double getWeight(Long itemAccessoryId) {
		ItemAccessory item = itemAccessoryRepository.findOne(itemAccessoryId);
		return item.getAccWeight();
	}
	public Page<ItemAccessory> findByDeleteYNPageableByProductId(Pageable pageable) {
		return itemAccessoryRepository.findByDeleteYNPageableByProductId(pageable);
	}
}
