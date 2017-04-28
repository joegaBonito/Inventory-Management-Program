package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.domain.ItemAccessory;
import com.obs.repositories.ItemAccessoryRepository;

@Service
public class ItemAccessoryService {
	
	private ItemAccessoryRepository itemAccessoryRepository;
	
	public ItemAccessoryService() {}
	@Autowired
	public ItemAccessoryService(ItemAccessoryRepository itemAccessoryRepository) {
		super();
		this.itemAccessoryRepository = itemAccessoryRepository;
	}
	
	public List<ItemAccessory> list(){ 
		return itemAccessoryRepository.findByDeleteYN();
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
	public Page<ItemAccessory> findByDeleteYNPageable(Pageable pageable) {
		return itemAccessoryRepository.findByDeleteYNPageable(pageable);
	}
}
